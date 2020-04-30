package com.architecture.model.network

import android.accounts.AuthenticatorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architecture.model.beans.SigninReq
import com.architecture.model.beans.SigninRes
import com.architecture.model.exception.ServerNotResponding
import com.architecture.model.network.retrofit.APIClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object APIHelper {
    val TAG = APIHelper::class.java.name

    private val apiClient = APIClient.getClient()

    fun signIn(email: String, pwd: String): LiveData<Result<SigninRes>> {
        var signinRes: MutableLiveData<Result<SigninRes>> = MutableLiveData()

        val request = SigninReq()
        request.email = email
        request.password = pwd

        val call = apiClient.signin(request)

        call.enqueue(object : Callback<SigninRes> {
            override fun onResponse(call: Call<SigninRes>, response: Response<SigninRes>) {
                if(response.isSuccessful) {
                    signinRes.value = Result.success(response.body()!!)
                } else if(response.code() == 400){
                    var message = "Something went wrong."
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        message = jObjError.getString("error")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        val exception = AuthenticatorException(message)
                        signinRes.value = Result.failure(exception)
                    }
                } else if(response.code() == 500) {
                    val exception = ServerNotResponding("Server not responding.")
                    signinRes.value = Result.failure(exception)
                }
            }

            override fun onFailure(call: Call<SigninRes>, t: Throwable) {
                val exception = Exception("Something went wrong.")
                signinRes.value = Result.failure(exception)
            }
        })

        return signinRes
    }
}
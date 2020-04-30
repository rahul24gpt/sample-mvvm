package com.architecture.model.repo

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architecture.model.network.APIHelper
import com.architecture.model.sharedpref.SharedPreferenceData
import com.architecture.model.sharedpref.TOKEN
import java.lang.Exception

object RepositoryImpl: Repository {
    val apiHelper = APIHelper
    val sharedPrefHelper = SharedPreferenceData

    override fun signIn(email: String, pwd: String): LiveData<Result<Boolean>> {
        var result: MutableLiveData<Result<Boolean>> = MutableLiveData()

        apiHelper.signIn(email, pwd).observeForever {
            it.onSuccess {
                // Save token in shared pref
                saveTokenInSharedPref(it.token)

                // Return result
                result.value = Result.success(true)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }

    override fun isLoggedIn(): LiveData<Boolean> {
        val savedToken = sharedPrefHelper.getString(TOKEN)
        return MutableLiveData(!TextUtils.isEmpty(savedToken))
    }

    override fun logout(): LiveData<Result<Boolean>> {
        sharedPrefHelper.clearData()

        return MutableLiveData(Result.success(true))
    }

    private fun saveTokenInSharedPref(token: String) {
        sharedPrefHelper.setData(TOKEN, token)
    }
}
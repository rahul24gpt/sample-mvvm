package com.architecture.model.network.retrofit

import com.architecture.model.beans.SigninReq
import com.architecture.model.beans.SigninRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @POST("login")
    @Headers("Accept:application/json")
    fun signin(@Body signinReq: SigninReq): Call<SigninRes>
}
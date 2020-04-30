package com.architecture.model.network.retrofit

import com.architecture.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {

    var apiInterface: APIInterface? = null

    fun getClient(): APIInterface {
        if(apiInterface == null) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(logging)
            val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build()

            apiInterface = builder.create(
                APIInterface::class.java)
        }

        return apiInterface!!
    }
}
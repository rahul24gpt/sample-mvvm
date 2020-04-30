package com.architecture.model.beans

import com.google.gson.annotations.SerializedName

class SigninRes {
    @SerializedName("token")
    lateinit var token: String
}
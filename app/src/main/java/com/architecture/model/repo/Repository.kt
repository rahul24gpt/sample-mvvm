package com.architecture.model.repo

import androidx.lifecycle.LiveData

interface Repository {
    fun signIn(email: String, pwd: String): LiveData<Result<Boolean>>
    fun isLoggedIn(): LiveData<Boolean>
    fun logout(): LiveData<Result<Boolean>>
}
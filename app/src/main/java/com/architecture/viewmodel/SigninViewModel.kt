package com.architecture.viewmodel

import android.accounts.AuthenticatorException
import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.architecture.R
import com.architecture.model.exception.ServerNotResponding
import com.architecture.model.repo.Repository
import com.architecture.util.ValidationUtils
import com.architecture.util.isNetworkAvailable
import java.lang.Exception

class SigninViewModel(private val context: Context, private val repository: Repository): ViewModel() {
    private var errMessage = MutableLiveData<String>()
    private var signinState = MediatorLiveData<SigninState>()
    init {
        checkIsAlreadySigin()
    }



    private fun checkIsAlreadySigin() {
        signinState.addSource(repository.isLoggedIn(), Observer {
            if(it) {
                signinState.value = SigninState.GO_TO_HOME_SCREEN
            }
        })
    }

    fun getErrMessage(): LiveData<String> {
        return errMessage
    }

    fun getSigninState(): LiveData<SigninState> {
        return signinState
    }

    fun onSigninClicked(email: String, pwd: String) {
        // Check validations
        if(TextUtils.isEmpty(email)) {
            // Email field is blank
            errMessage.value = context.getString(R.string.err_blank_email)
            return
        } else if(!ValidationUtils.isValidEmail(email)) {
            // Invalid email id
            errMessage.value = context.getString(R.string.err_invalid_email)
            return
        } else if(TextUtils.isEmpty(pwd)) {
            // Empty password
            errMessage.value = context.getString(R.string.err_blank_pwd)
            return
        }

        // Check internet connection
        if(!context.isNetworkAvailable()) {
            // No internet
            errMessage.value = context.getString(R.string.err_no_internet)
            return
        }

        // Hit api
        signinState.value = SigninState.SHOW_PROGRESS
        signinState.addSource(repository.signIn(email, pwd), Observer {
            it.onSuccess {
                signinState.value = SigninState.HIDE_PROGRESS
                signinState.value = SigninState.GO_TO_HOME_SCREEN
            }
            it.onFailure {
                signinState.value = SigninState.HIDE_PROGRESS
                when(it){
                    is AuthenticatorException ->{
                        errMessage.value = it.message
                    }
                    is ServerNotResponding ->{
                        errMessage.value = it.message
                    }
                    is Exception -> {
                        errMessage.value = it.message
                    }
                }
            }
        })
    }

    enum class SigninState {
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        GO_TO_HOME_SCREEN
    }
}
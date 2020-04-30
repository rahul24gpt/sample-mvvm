package com.architecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architecture.MyApp
import com.architecture.model.repo.Repository
import com.architecture.model.repo.RepositoryImpl

class MyViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SigninViewModel::class.java)) {
            return SigninViewModel(MyApp.mInstance.applicationContext, RepositoryImpl as Repository) as T
        } else if(modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(MyApp.mInstance.applicationContext, RepositoryImpl as Repository) as T
        } else if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MyApp.mInstance.applicationContext, RepositoryImpl as Repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}
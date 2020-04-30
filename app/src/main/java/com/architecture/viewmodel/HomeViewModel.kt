package com.architecture.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.architecture.model.repo.Repository

class HomeViewModel(private val context: Context, private val repository: Repository): ViewModel() {

    private var homeState: MediatorLiveData<HomeState> = MediatorLiveData()

    fun getHomeState(): LiveData<HomeState>{
        return homeState
    }

    fun logout() {
        homeState.addSource(repository.logout(), Observer {
            it.onSuccess {
                homeState.value = HomeState.GO_TO_LOGIN_PAGE
            }
        })
    }

    enum class HomeState{
        GO_TO_LOGIN_PAGE
    }
}
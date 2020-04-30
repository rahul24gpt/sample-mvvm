package com.architecture.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.architecture.R
import com.architecture.viewmodel.HomeViewModel
import com.architecture.viewmodel.MyViewModelFactory
import com.architecture.viewmodel.SigninViewModel
import kotlinx.android.synthetic.main.a_home.*

class HomeActivity : BaseActivity(), View.OnClickListener {

    // Init view model lazy
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_home)

        setListeners()
        setObservers()
    }

    private fun setObservers() {
        mViewModel.getHomeState().observe(this, Observer {
            when(it) {
                HomeViewModel.HomeState.GO_TO_LOGIN_PAGE->
                    goToLoginPage()
            }
        })
    }

    private fun goToLoginPage() {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setListeners() {
        txt_logout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if(view == txt_logout) {
            mViewModel.logout()
        }
    }
}

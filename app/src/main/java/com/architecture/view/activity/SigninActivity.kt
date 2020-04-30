package com.architecture.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.architecture.R
import com.architecture.util.showToast
import com.architecture.viewmodel.MyViewModelFactory
import com.architecture.viewmodel.SigninViewModel
import kotlinx.android.synthetic.main.a_signin.*

class SigninActivity : BaseActivity(), View.OnClickListener {

    // Init view model lazy
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(SigninViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_signin)

        setListeners()
        setObservers()
    }

    private fun setListeners() {
        btn_signin.setOnClickListener(this)
        txt_singup.setOnClickListener(this)
    }

    /**
     * Observe live data in viewmodel
     */
    private fun setObservers() {
        // Observer error message
        mViewModel.getErrMessage().observe(this, Observer {
            showToast(it)
        })


        // Observe login state
        mViewModel.getSigninState().observe(this, Observer {
            when(it) {
                SigninViewModel.SigninState.SHOW_PROGRESS ->
                    showProgress()
                SigninViewModel.SigninState.HIDE_PROGRESS ->
                    hideProgress()
                SigninViewModel.SigninState.GO_TO_HOME_SCREEN ->
                    goToHomeScreen()
            }
        })
    }

    /**
     * Go to signup screen
     */
    private fun goToSignupScreen() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    /**
     * Go to home screen
     */
    private fun goToHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(view: View?) {
        if(view == btn_signin) {
            onSigninClicked()
        } else if(view == txt_singup) {
            goToSignupScreen()
        }
    }

    private fun onSigninClicked() {
        mViewModel.onSigninClicked(edit_email.text.toString(), edit_pwd.text.toString())
    }
}
package com.architecture.view.activity

import androidx.appcompat.app.AppCompatActivity
import com.architecture.view.dialog.CustomProgressBar

abstract class BaseActivity: AppCompatActivity() {
    private var progressBar: CustomProgressBar? = null

    open fun showProgress() {
        progressBar = CustomProgressBar()
        progressBar!!.show(this)
    }

    open fun hideProgress() {
        progressBar?.getDialog()?.dismiss()
    }
}
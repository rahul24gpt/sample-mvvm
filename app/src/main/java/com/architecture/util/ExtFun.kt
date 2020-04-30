package com.architecture.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun Context.isNetworkAvailable(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}
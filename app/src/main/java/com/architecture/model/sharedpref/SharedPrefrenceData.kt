package com.architecture.model.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.architecture.MyApp

private const val MYAPP_PREF = "com.architecture"

object SharedPreferenceData {
    private val prefs: SharedPreferences = MyApp.mInstance.getSharedPreferences(MYAPP_PREF, Context.MODE_PRIVATE)

    fun setData(key: String, value: Any) {
        val editor = prefs.edit()
        when(value){
            is String->{
                editor.putString(key, value)
            }
            is Int ->{
                editor.putInt(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
            is Float -> {
                editor.putFloat(key, value)
            }
            is Boolean -> {
                editor.putBoolean(key, value)
            }
        }
        editor.apply()
    }

    fun getString(key: String): String? {
        return prefs.getString(key, "")
    }

    fun getInt(key: String, defaultValue: Int = 0): Int{
        return prefs.getInt(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long = 0): Long{
        return prefs.getLong(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float = 0f): Float{
        return prefs.getFloat(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    fun clearData() {
        val editor = prefs.edit()
        editor.clear().apply()
    }
}
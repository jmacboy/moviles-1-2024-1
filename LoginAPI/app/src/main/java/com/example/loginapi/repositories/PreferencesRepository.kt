package com.example.loginapi.repositories

import android.content.Context
import android.content.SharedPreferences

object PreferencesRepository {
    fun saveToken(token: String, context: Context?) {
        val sharedPref = context?.getSharedPreferences(
            "proyecto-final", Context.MODE_PRIVATE
        )
        with(sharedPref?.edit()) {
            this?.putString("token", token)
            this?.apply()
        }
    }
    fun getToken(context: Context?): String? {
        val sharedPref = context?.getSharedPreferences(
            "proyecto-final", Context.MODE_PRIVATE
        )
        return sharedPref?.getString("token", null)
    }
}
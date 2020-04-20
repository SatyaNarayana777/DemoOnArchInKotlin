package com.kotlin.demoinkotlin.data.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferences @Inject constructor(private val mPrefs: SharedPreferences) {

    private val IS_USER_LOGIN : String = "IS_USER_LOGIN"

    fun isUserLogin(): Boolean {
        return mPrefs.getBoolean(IS_USER_LOGIN, false)
    }

    fun setIsUserLogin(isUserLogin: Boolean) {
        mPrefs.edit().putBoolean(IS_USER_LOGIN, isUserLogin).apply()
    }

    fun clear() {
        mPrefs.edit().clear().apply()
    }

}

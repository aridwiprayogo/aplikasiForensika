package com.project.forensika.preferences

import android.content.Context
import androidx.core.content.edit

class SharedPreferencesUtils(context: Context) {
    private val sharedPreferences =
            context.getSharedPreferences("forensika pref", Context.MODE_PRIVATE)

    var token: String
        get() {
            return sharedPreferences.getString(TOKEN_USER,"") ?: ""
        }
        set(value) {
            sharedPreferences.edit {
                putString(TOKEN_USER, "Bearer $value")
            }
        }

    companion object {
        private const val TOKEN_USER: String = "token user"
    }
}
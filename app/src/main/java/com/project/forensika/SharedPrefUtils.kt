package com.project.forensika

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.project.forensika.BuildConfig

/**
 * A pack of helpful getter and setter methods for reading/writing to [SharedPreferences].
 */
object SharedPrefUtils {
    const val IS_LOGIN = "IS_LOGIN"
    const val LOKASI = "LOKASI"

    fun getStringPreference(context: Context, key: String?): String? {
        var value: String? = null
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            value = preferences.getString(key, null)
        }
        return value
    }

    fun setStringPreference(context: Context, key: String?, value: String?): Boolean {
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null && !TextUtils.isEmpty(key)) {
            val editor = preferences.edit()
            editor.putString(key, value)
            return editor.commit()
        }
        return false
    }

    fun getFloatPreference(context: Context, key: String?, defaultValue: Float): Float {
        var value = defaultValue
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            value = preferences.getFloat(key, defaultValue)
        }
        return value
    }

    fun setFloatPreference(context: Context, key: String?, value: Float): Boolean {
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putFloat(key, value)
            return editor.commit()
        }
        return false
    }

    fun getLongPreference(context: Context, key: String?, defaultValue: Long): Long {
        var value = defaultValue
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            value = preferences.getLong(key, defaultValue)
        }
        return value
    }

    fun setLongPreference(context: Context, key: String?, value: Long): Boolean {
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putLong(key, value)
            return editor.commit()
        }
        return false
    }

    fun getIntegerPreference(context: Context, key: String?, defaultValue: Int): Int {
        var value = defaultValue
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            value = preferences.getInt(key, defaultValue)
        }
        return value
    }

    fun setIntegerPreference(context: Context, key: String?, value: Int): Boolean {
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putInt(key, value)
            return editor.commit()
        }
        return false
    }

    fun getBooleanPreference(context: Context, key: String?, defaultValue: Boolean): Boolean {
        var value = defaultValue
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            value = preferences.getBoolean(key, defaultValue)
        }
        return value
    }

    fun setBooleanPreference(context: Context, key: String?, value: Boolean): Boolean {
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putBoolean(key, value)
            return editor.commit()
        }
        return false
    }

    fun logOut(context: Context): Boolean {
        val preferences = context.getSharedPreferences(BuildConfig.sessionname, Context.MODE_PRIVATE)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.clear()
            return editor.commit()
        }
        return false
    }
}
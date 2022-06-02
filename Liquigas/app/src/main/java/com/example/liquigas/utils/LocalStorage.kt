package com.example.liquigas.utils


import android.content.Context;
import android.content.SharedPreferences;

class LocalStorage(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE)
    private val defaultValue = "defaultValue"

    fun getValue(key: String) : String? {
        return preferences.getString(key, defaultValue)
    }

    fun setValue(key: String, value: String) {
        preferences.edit().putString(key, value).commit()
    }
}
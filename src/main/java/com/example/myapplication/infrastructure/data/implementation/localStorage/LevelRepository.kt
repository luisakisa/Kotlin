package com.example.myapplication.infrastructure.data.implementation.localStorage

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.infrastructure.data.`in`.ILevelRepository

class LevelRepository: ILevelRepository {
    private lateinit var preference: SharedPreferences

    fun initialize(context: Context) {
        preference = context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)
    }

    override suspend fun setLevel(value: Int) {
        val editor = preference.edit()
        editor.putInt("level", value)
        editor.apply()
    }

    override suspend fun getLevel(): Int {
        return preference.getInt("level", 0)
    }
}
package com.example.myapplication.infrastructure.data.factories

import com.example.myapplication.infrastructure.data.implementation.localStorage.LevelRepository as localStorage
import com.example.myapplication.infrastructure.data.`in`.ILevelRepository

object LevelFabric {
    fun createInstanceRepository(flag: String): ILevelRepository {
        return when (flag) {
//            "api" -> API()
            "ls" -> localStorage()
            else -> throw IllegalArgumentException("Invalid flag")
        }
    }
}
package com.example.myapplication.application.factories

import com.example.myapplication.application.implementation.LevelService
import com.example.myapplication.application.`in`.ILevelService

object LevelServiceFabric {
    fun createInstanceService(flag: String): ILevelService {
        return when (flag) {
            "ls" -> LevelService()
            else -> throw IllegalArgumentException("Invalid flag")
        }
    }
}
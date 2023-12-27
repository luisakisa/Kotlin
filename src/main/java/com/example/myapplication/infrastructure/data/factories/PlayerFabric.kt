package com.example.myapplication.infrastructure.data.factories

import com.example.myapplication.infrastructure.data.implementation.dataBase.PlayerRepository as API
import com.example.myapplication.infrastructure.data.`in`.IPlayerRepository

object PlayerFabric {
    fun createInstanceRepository(flag: String): IPlayerRepository {
        return when (flag) {
            "api" -> API()
//            "ls" -> PlRepository()
            else -> throw IllegalArgumentException("Invalid flag")
        }
    }
}
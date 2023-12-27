package com.example.myapplication.application.factories

import com.example.myapplication.application.implementation.PlayerService
import com.example.myapplication.application.`in`.IPlayerService

object PlayerServiceFabric {
    fun createInstanceService(flag: String): IPlayerService {
        return when (flag) {
            "ps" -> PlayerService()
            else -> throw IllegalArgumentException("Invalid flag")
        }
    }
}
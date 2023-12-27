package com.example.myapplication.application.`in`

import com.example.myapplication.application.domain.entities.Level

interface ILevelService {
    suspend fun completeLevel()

    suspend fun getLevel(): Int

    fun getLevelData(level:Int): Level
}
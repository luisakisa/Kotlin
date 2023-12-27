package com.example.myapplication.application.implementation
import com.example.myapplication.application.`in`.ILevelService
import com.example.myapplication.application.domain.Levels
import com.example.myapplication.application.domain.entities.Level
import com.example.myapplication.infrastructure.data.`in`.ILevelRepository
import com.example.myapplication.infrastructure.data.factories.LevelFabric

class LevelService : ILevelService {
    val rep: ILevelRepository = LevelFabric.createInstanceRepository("ls")
    override suspend fun completeLevel() {
        rep.setLevel(rep.getLevel() + 1)
    }

    override suspend fun getLevel(): Int {
    return rep.getLevel()
    }

    override fun getLevelData(level:Int): Level {return Levels.getLevelData(level)}
}
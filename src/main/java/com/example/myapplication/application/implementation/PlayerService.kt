package com.example.myapplication.application.implementation

import com.example.myapplication.application.`in`.IPlayerService
import com.example.myapplication.infrastructure.data.`in`.IPlayerRepository
import com.example.myapplication.infrastructure.data.factories.PlayerFabric

class PlayerService : IPlayerService {
    val rep: IPlayerRepository = PlayerFabric.createInstanceRepository("api")

    override suspend fun getName(): String {
        return rep.getPlayer(1)[0].name
    }

    override suspend fun getAge(): Int {
        return rep.getPlayer(1)[0].age
    }
    override suspend fun del() {
        rep.delPlayer(1)
    }
}
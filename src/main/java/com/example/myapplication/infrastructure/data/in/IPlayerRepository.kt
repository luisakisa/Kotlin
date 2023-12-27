package com.example.myapplication.infrastructure.data.`in`

import com.example.myapplication.application.domain.entities.Player

interface IPlayerRepository {
    suspend fun getPlayer(id: Int): List<Player>;
    suspend fun addPlayer(player: Player): String;
    suspend fun delPlayer(id: Int);
}
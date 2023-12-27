package com.example.myapplication.infrastructure.data.implementation.dataBase

import com.example.myapplication.application.domain.entities.Player
import com.example.myapplication.infrastructure.data.`in`.IPlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PlayerRepository : IPlayerRepository {
    override suspend fun getPlayer(id: Int): List<Player> = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://localhost:8080/childgame1-1.0-SNAPSHOT/api/player$id")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) continuation.resume(emptyList())
                val responseBody = response.body?.string()
                val playerArray = _response(responseBody)
                continuation.resume(playerArray)
            }
        }
    }

    override suspend fun addPlayer(player: Player): String = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            val client = OkHttpClient()
            val requestBody = JSONObject().apply {
                put("age", player.age)
                put("id", player.id)
                put("level", player.level)
                put("name", player.name)
            }.toString().toRequestBody("application/json".toMediaType())

            val request = Request.Builder()
                .url("http://localhost:8080/childgame1-1.0-SNAPSHOT/api/adding")
                .method("POST", requestBody)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) continuation.resume("Failed to add player")
                val responseData = response.body?.string() ?: "Player added successfully"
                continuation.resume(responseData)
            }
        }
    }

    override suspend fun delPlayer(id: Int) {
        val URL: String = "http://localhost:8080/childgame1-1.0-SNAPSHOT/api/deleting/$id"
        del(URL)
    }

    private fun _response(playerJson: String?): List<Player> {
        return try {
            val jsonArray = JSONObject(playerJson).getJSONArray("players")
            List(jsonArray.length()) { index ->
                val playerObj = jsonArray.getJSONObject(index)
                Player(
                    playerObj.getInt("age"),
                    playerObj.getInt("id"),
                    playerObj.getInt("level"),
                    playerObj.getString("name")
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun del(URL: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(URL)
            .delete()
            .build()

        client.newCall(request).execute()
    }
}


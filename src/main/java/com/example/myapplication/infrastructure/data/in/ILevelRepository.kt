package com.example.myapplication.infrastructure.data.`in`

interface ILevelRepository {
     suspend fun setLevel(value: Int);
    suspend fun getLevel(): Int;
}
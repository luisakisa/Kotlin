package com.example.myapplication.application.domain

import com.example.myapplication.application.domain.entities.Level

object Levels {
    fun getLevelData(currentLevel: Int): Level {
        return when (currentLevel) {
            1, 2 -> Level(figures = listOf("square"))
            3 -> Level(figures = listOf("triangle"))
            4 -> Level(figures = listOf("circle"))
            5 -> Level(figures = listOf("square", "circle"))
            6 -> Level(figures = listOf("square", "circle"), commonColor = true)
            7 -> Level(figures = listOf("square", "circle", "triangle"))
            8 -> Level(figures = listOf("square", "circle", "triangle"), commonColor = true)
            else -> Level(figures = emptyList()) // Default case
        }
    }
     val countLevel = 8
}
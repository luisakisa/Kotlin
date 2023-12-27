package com.example.myapplication.presentation.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.application.factories.LevelServiceFabric
import com.example.myapplication.application.`in`.ILevelService
import com.example.myapplication.application.domain.Levels
import com.example.myapplication.presentation.view.components.LevelCard
import com.example.myapplication.presentation.view.components.MainHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
 fun Levels(navController: NavHostController) {
    val countLevels = remember{ Levels.countLevel}
    var currentLevel = remember {1}
    val ls: ILevelService = LevelServiceFabric.createInstanceService("ls");

    LaunchedEffect(Unit) {
        try {
            val result = withContext(Dispatchers.IO) {
                ls.getLevel()
            }
            currentLevel = result
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            MainHeader(
                title = "Уровни",
                onSettingsPress = { }
            )
        }

        items(countLevels) { index ->
            val levelText: String = if (index < currentLevel) {
                "уровень - пройден"
            } else {
                "уровень"
            }
            LevelItem(index+1,levelText, onLevelPress = { level ->  navController.navigate("Game?level=${level}") })
        }
    }
}

@Composable
fun LevelItem(level: Int,levelText: String, onLevelPress: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onLevelPress(level) }
    ) {    LevelCard(level = level, text = levelText)
    }

}

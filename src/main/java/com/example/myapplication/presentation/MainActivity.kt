package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.presentation.view.screens.EndGame
import com.example.myapplication.presentation.view.screens.Game
import com.example.myapplication.presentation.view.screens.Levels

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                color = Color(0xFF5A3ECD)
            ){
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Levels" ){
                composable("Levels"){
                    Levels(navController)
                }
                composable(
                    "Game?level={level}",
                    arguments = listOf(navArgument(name = "level"){
                        type = NavType.IntType
                        defaultValue = 1
                    })
                ) { backStackEntry ->
                    val level = backStackEntry.arguments?.getInt("level")
                    if (level != null) {
                        Game(navController, level = level)
                    }
                }
                composable("EndGame"){
                    EndGame(navController)
                }
            }}
        }
    }
}
@Composable
fun text(count: Int) {
    Text(text = "Счетчик: $count")
}

@Composable
fun button(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Увеличить счетчик")
    }
}

@Composable
fun CounterApp() {
    var count = remember { mutableStateOf(0) }
    Column {
        text(count.value)
        button(onClick = { count.value += 1 })
    }
}


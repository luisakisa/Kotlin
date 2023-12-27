package com.example.myapplication.presentation.view.screens

import android.annotation.SuppressLint
import androidx.compose.ui.geometry.Rect
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.presentation.view.components.MainHeader
import kotlin.math.roundToInt
import com.example.myapplication.application.factories.LevelServiceFabric
import com.example.myapplication.application.`in`.ILevelService

@SuppressLint("UnrememberedMutableState")
@Composable
 fun Game(navController: NavHostController, level: Int) {
    var figures by remember { mutableStateOf(emptyList<String>()) }
    val ls: ILevelService = LevelServiceFabric.createInstanceService("ls");
    figures = ls.getLevelData(level).figures

    fun getFigureInitialState(fig: String): Boolean{
        return if (figures.isNotEmpty()) {
            !figures.contains(fig);
        } else true
    }
    var circleCorrect by remember { mutableStateOf(getFigureInitialState("circle"))}
    var triangleCorrect by remember { mutableStateOf(getFigureInitialState("triangle"))}
    var squareCorrect by remember { mutableStateOf(getFigureInitialState("square"))}

    val initialPositions = mutableStateMapOf(
        "circle" to Offset(150f, 180f),
        "triangle" to Offset(170f, 80f),
        "square" to Offset(250f, 80f)
    )
    LaunchedEffect(circleCorrect, triangleCorrect, squareCorrect) {
        if (circleCorrect) {
//            completeLevel()
            navController.navigate("EndGame")}
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            MainHeader(
                title = "Уровень $level",
                onSettingsPress = { }
            )
        }

       item{ Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        )
        if (figures.contains("circle")) {
            val boxSize = 120.dp
            val boxPosition = IntOffset(40, 40)  // Установите
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Magenta, RoundedCornerShape(100.dp))
                    .size(boxSize)
                    .offset(boxPosition.x.dp, boxPosition.y.dp)
            )
            DraggableFigure(
                figureType = "circle",
                initialPosition = initialPositions["circle"]!!,
                onDragRelease = { position ->
                    val circleSize = 120.dp  // Установите реальный размер DraggableFigure

                    val circleBounds = Rect(
                        position.x,
                        position.y,
                        position.x + circleSize.value,
                        position.y + circleSize.value
                    )

                    val boxBounds = Rect(
                        boxPosition.x.toFloat(),
                        boxPosition.y.toFloat(),
                        boxPosition.x.toFloat() + boxSize.value,
                        boxPosition.y.toFloat() + boxSize.value
                    )
                    if (!circleBounds.intersect(boxBounds).isEmpty) {
                        circleCorrect = true
                    }
                }
            )
        }

        if (figures.contains("triangle")) {
            DraggableFigure(
                figureType = "triangle",
                initialPosition = initialPositions["triangle"]!!,
                onDragRelease = { position ->
                    if (
                        position.x >= 130 &&
                        position.x <= 255 &&
                        position.y >= 310 &&
                        position.y <= 420
                    ) {
                        triangleCorrect = true
                    }
                }
            )
        }

        if (figures.contains("square")) {
            DraggableFigure(
                figureType = "square",
                initialPosition = initialPositions["square"]!!,
                onDragRelease = { position ->
                    if (
                        position.x >= 135 &&
                        position.x <= 255 &&
                        position.y >= 450 &&
                        position.y <= 560
                    ) {
                        squareCorrect = true
                    }
                }
            )
        }

    }}}


@Composable
fun DraggableFigure(
    figureType: String,
    initialPosition: Offset,
    onDragRelease: (Offset) -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(initialPosition.x) }
    var offsetY by remember { mutableFloatStateOf(initialPosition.y) }
when(figureType){ "square" ->  Box(
    modifier = Modifier
        .offset {
            IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
        }
        .background(Color.Blue)
        .size(80.dp)
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                offsetX += dragAmount.x
                offsetY += dragAmount.y
            }
        }

)
    "circle" -> Box(
        modifier = Modifier
            .offset {
                IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
            }
            .size(120.dp)
            .background(Color.Green, shape = CircleShape)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    )
    "triangle" -> Box(
        modifier = Modifier
            .offset {
                IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
            }
            .size(80.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    )

}
   }
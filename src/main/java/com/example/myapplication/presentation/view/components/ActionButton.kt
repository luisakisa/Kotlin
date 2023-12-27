package com.example.myapplication.presentation.view.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp


@Composable
fun ActionButton(
    title: String,
    onClick: () -> Unit,
    fixedToBottom: Boolean = false,
) {      Column(
    modifier = if (fixedToBottom) Modifier.fillMaxHeight().fillMaxWidth() else Modifier.fillMaxWidth().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    if (!fixedToBottom) {
        Spacer(modifier = Modifier.weight(16f))
    }
    Button(onClick = onClick ) {
        Text(text = title)

    }
    if (fixedToBottom) {
        Spacer(modifier = Modifier.weight(16f))
    }
}
}

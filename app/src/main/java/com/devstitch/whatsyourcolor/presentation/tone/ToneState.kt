package com.devstitch.whatsyourcolor.presentation.tone

import androidx.compose.ui.graphics.Color

data class ToneState(
    val text1: String = "",
    val text2: String = "",
    val text3: String = "",
    val color1: Color? = null,
    val color2: Color? = null,
    val color3: Color? = null
)

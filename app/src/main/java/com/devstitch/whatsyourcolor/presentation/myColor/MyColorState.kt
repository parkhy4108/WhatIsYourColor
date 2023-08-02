package com.devstitch.whatsyourcolor.presentation.myColor

import com.devstitch.whatsyourcolor.domain.MyColor

data class MyColorState(
    val colors: List<MyColor> = emptyList()
)
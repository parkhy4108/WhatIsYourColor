package com.devstitch.whatsyourcolor.presentation.colorList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.graphics.Color

data class ColorListState @OptIn(ExperimentalFoundationApi::class) constructor(
    val selectedColor : Int = 0,
    val selectedIndex: Int = 0,
    val expand: Boolean = false,
    val colorList : List<Color> = emptyList(),
    val pagerState: PagerState = PagerState(0,0f)

)
package com.devstitch.whatsyourcolor.presentation.myPalette

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import com.devstitch.whatsyourcolor.domain.MyColor

data class MyPaletteState @OptIn(ExperimentalFoundationApi::class) constructor(
    val colorList: List<MyColor> = emptyList(),
    val expand: Boolean = false,
    val pagerState: PagerState = PagerState(),
    val isListBtnClicked: Boolean = false,
    val showDialog: Boolean = false
)
package com.devstitch.whatsyourcolor.presentation.colorList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.navigation.Screen
import com.devstitch.whatsyourcolor.presentation.BackHandler

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColorListScreen(
    season: String,
    tone: String,
    colorListViewModel: ColorListViewModel = hiltViewModel(),
    openScreen: (String) -> Unit,
    popUpScreen: () -> Unit
) {
    val state by colorListViewModel.colorListState

    LaunchedEffect(Unit) {
        colorListViewModel.init(season, tone)
    }

    DisposableEffect(key1 = true) {
        onDispose {

        }
    }

    BackHandler(true) {
        if (state.expand) {
            colorListViewModel.onChangedExpandState()
        } else {
            popUpScreen()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            state.colorList.chunked(4).forEachIndexed { row, colors: List<Color> ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    colors.forEachIndexed { index, color ->
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(color)
                            .clickable {
                                colorListViewModel.clickedBox(color.toArgb(), index)
                                colorListViewModel.onChangedPagerState(row * 4 + index)
                            }
                        )
                    }
                }
            }
        }
        IconButton(
            onClick = { popUpScreen() },
            modifier = Modifier.align(Alignment.TopStart).padding(4.dp,0.dp,0.dp,0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "BackButton",
                tint = Color.Black
            )
        }
        IconButton(
            onClick = { openScreen(Screen.MyColorScreen.route) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp,0.dp)
                .clip(shape = CircleShape)
        ) {
            Icon(painter = painterResource(id = R.drawable.palette), contentDescription = "MyColor")
        }
        AnimatedVisibility(
            visible = state.expand,
            enter = fadeIn(tween(300)),
            exit = fadeOut(tween(300))
        ) {
            HorizontalPager(
                state = state.pagerState,
                key = { state.colorList[it].toArgb() },
                pageSize = PageSize.Fill,
                pageCount = state.colorList.size
            ) { index ->
                val color = state.colorList[index].toArgb()
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(color))
                    .clickable {
                        colorListViewModel.onChangedExpandState()
                    }
                ) {
                    IconButton(
                        onClick = { colorListViewModel.saveColor(color) },
                        modifier = Modifier.align(Alignment.TopEnd).padding(4.dp,0.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.add), contentDescription = "addColor")
                    }
                }
            }
        }
    }


}


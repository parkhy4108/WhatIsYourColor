package com.devstitch.whatsyourcolor.presentation.colorList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.common.navigation.Screen
import com.devstitch.whatsyourcolor.presentation.BackHandler
import com.devstitch.whatsyourcolor.presentation.composable.StandardIconButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColorListScreen(
    season: String,
    tone: String,
    colorListViewModel: ColorListViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
    openScreen: (String) -> Unit,
    popUpScreen: () -> Unit
) {
    val state by colorListViewModel.state
    val coroutineScope = rememberCoroutineScope()

    val saveOkay = stringResource(id = R.string.save)
    val saveNot = stringResource(id = R.string.saveNot)
    LaunchedEffect(Unit) { colorListViewModel.init(season, tone) }
    BackHandler(true) {
        if (state.expand) colorListViewModel.onChangedExpandState()
        else popUpScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp, 8.dp, 4.dp, 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            state.colorList.chunked(4).forEachIndexed { row, colors: List<Color> ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    colors.forEachIndexed { index, color ->
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .clickable { colorListViewModel.clickedBox(row, index) }
                            )
                        }
                    }
                }
            }
        }
        IconButton(
            onClick = { popUpScreen() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(4.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "BackButton",
                tint = Color.Black
            )
        }
        IconButton(
            onClick = { openScreen(Screen.MyPaletteScreen.route) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp, 0.dp)
                .clip(shape = CircleShape)
        ) {
            Icon(painter = painterResource(id = R.drawable.palette), contentDescription = "MyColor")
        }

    }

    AnimatedVisibility(
        visible = state.expand,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = state.pagerState,
                key = { state.colorList[it].toArgb() },
                pageSize = PageSize.Fill,
                pageCount = state.colorList.size
            ) { index ->
                val color = state.colorList[index].toArgb()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(color))
                )
            }
            StandardIconButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(4.dp, 0.dp, 0.dp, 0.dp),
                painter = R.drawable.back,
                onClick = { colorListViewModel.onChangedExpandState() }
            )
            StandardIconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp, 0.dp),
                painter = R.drawable.add,
                onClick = { colorListViewModel.saveColor(state.colorList[state.pagerState.currentPage].toArgb()) }
            )
        }

    }


}


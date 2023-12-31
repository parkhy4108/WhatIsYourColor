package com.devstitch.whatsyourcolor.presentation.colorList

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.common.navigation.Screen
import com.devstitch.whatsyourcolor.presentation.composable.BackHandler
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
    val saveSuccess = stringResource(id = R.string.save)
    val saveFail = stringResource(id = R.string.saveNot)
    val configuration = LocalConfiguration.current
    val columnCount = when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> 4
        else -> 8
    }

    LaunchedEffect(Unit) { colorListViewModel.init(season, tone) }

    if (state.isSaveSuccess) {
        LaunchedEffect(snackBarHostState) {
            snackBarHostState.showSnackbar(saveSuccess)
            colorListViewModel.onChangedSuccessState(false)
        }
    }

    if (state.isSaveFailure) {
        LaunchedEffect(snackBarHostState) {
            snackBarHostState.showSnackbar(saveFail)
            colorListViewModel.onChangedFailureState(false)
        }
    }

    BackHandler {
        if (state.expand) colorListViewModel.onChangedExpandState()
        else popUpScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp, 5.dp, 5.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(columnCount),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
            ) {
                itemsIndexed(state.colorList) { index: Int, item: Color ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f / 1.5f)
                            .padding(3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(item)
                                .clickable { colorListViewModel.clickedBox(index) }
                        )
                    }
                }
            }
        }
        StandardIconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(4.dp, 0.dp, 0.dp, 0.dp),
            painter = R.drawable.back,
            onClick = { popUpScreen() }
        )
        StandardIconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp, 0.dp)
                .clip(shape = CircleShape),
            painter = R.drawable.palette,
            onClick = { openScreen(Screen.MyPaletteScreen.route) }
        )
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
package com.devstitch.whatsyourcolor.presentation.myPalette

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.presentation.composable.BackHandler
import com.devstitch.whatsyourcolor.presentation.composable.DeleteDialog
import com.devstitch.whatsyourcolor.presentation.composable.StandardIconButton
import com.devstitch.whatsyourcolor.presentation.composable.StandardText
import com.devstitch.whatsyourcolor.R.string as AppText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPaletteScreen(
    popUpScreen: () -> Unit,
    myPaletteViewModel: MyPaletteViewModel = hiltViewModel()
) {
    val state by myPaletteViewModel.state
    val mapState = myPaletteViewModel.mapState
    val configuration = LocalConfiguration.current
    val columnCount = when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> 4
        else -> 8
    }


    LaunchedEffect(Unit) { myPaletteViewModel.init() }

    BackHandler(true) {
        if (state.expand) myPaletteViewModel.onChangedExpandState()
        else popUpScreen()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp,5.dp,20.dp, 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StandardIconButton(
                painter = R.drawable.back,
                onClick = { popUpScreen() }
            )
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StandardText(text = AppText.My)
                Icon(painter = painterResource(id = R.drawable.palette), contentDescription = null)
                StandardText(text = AppText.Palette)
            }
            val isChecked = if (!state.isListBtnClicked) R.drawable.checklist else R.drawable.delete
            StandardIconButton(
                painter = isChecked,
                enabled = state.colorList.isNotEmpty(),
                onClick = {
                    if (state.isListBtnClicked && mapState.containsValue(true)) {
                        myPaletteViewModel.deleteButtonClicked()
                    } else {
                        myPaletteViewModel.onChangedCheckedState()
                    }
                }
            )

        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(columnCount),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
        ) {
            itemsIndexed(state.colorList) { index, item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(6.dp)
                        .clickable { myPaletteViewModel.clickedBox(index) },
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(item.colorRGB))
                    ) {
                        if (state.isListBtnClicked) {
                            IconButton(
                                modifier = Modifier.align(Alignment.BottomEnd),
                                onClick = {
                                    myPaletteViewModel.onChangedCheckedMapState(newCheckedId = item.id)
                                }
                            ) {
                                val tintColor =
                                    if (!mapState[item.id]!!) Color.LightGray else Color.Yellow
                                Icon(
                                    painter = painterResource(id = R.drawable.circle),
                                    contentDescription = null,
                                    tint = tintColor
                                )
                            }
                        }
                    }

                }
            }
        }
    }

    AnimatedVisibility(
        visible = state.expand,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300))
    ) {
        HorizontalPager(
            state = state.pagerState,
            key = { state.colorList[it].colorRGB },
            pageSize = PageSize.Fill,
            pageCount = state.colorList.size
        ) { index ->
            val color = state.colorList[index].colorRGB
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(color))
                .clickable { myPaletteViewModel.onChangedExpandState() }
            )
        }
    }

    if (state.showDialog) {
        DeleteDialog(
            onDismiss = { myPaletteViewModel.cancelDialog() },
            onConfirm = { myPaletteViewModel.onDialogConfirmClicked() }
        )
    }

}
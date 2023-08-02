package com.devstitch.whatsyourcolor.presentation.myColor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.presentation.AdView

@Composable
fun MyColorsScreen(
    popUpScreen: () -> Unit,
    myColorViewModel: MyColorViewModel = hiltViewModel()
) {

    val state by myColorViewModel.state

    LaunchedEffect(Unit) {
        myColorViewModel.init()
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        LazyVerticalGrid(columns = GridCells.Fixed(4)) {

        }
        IconButton(
            onClick = { popUpScreen() },
            modifier = Modifier.align(Alignment.TopStart).padding(4.dp,0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "BackButton",
                tint = Color.Black
            )
        }
    }
}
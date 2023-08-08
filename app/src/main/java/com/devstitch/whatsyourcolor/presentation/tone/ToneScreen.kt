package com.devstitch.whatsyourcolor.presentation.tone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.common.navigation.Screen
import com.devstitch.whatsyourcolor.presentation.composable.StandardIconButton
import com.devstitch.whatsyourcolor.presentation.composable.StandardText
import com.devstitch.whatsyourcolor.presentation.composable.ToneColorItem

@Composable
fun ToneScreen(
    season: String,
    openScreen: (String) -> Unit,
    popUp: () -> Unit,
    viewModel: ToneViewModel = hiltViewModel()
) {
    val state by viewModel.state

    LaunchedEffect(Unit) { viewModel.init(season) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 5.dp, 20.dp, 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StandardIconButton(painter = R.drawable.back, onClick = { popUp() })
            StandardText(text = R.string.tone)
            StandardIconButton(painter = R.drawable.palette, onClick = { openScreen(Screen.MyPaletteScreen.route) })
        }
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ToneColorItem(modifier = Modifier.weight(1f), text = state.text1, color = state.color1) {
                    openScreen(Screen.ColorScreen.passSeasonAndTone(season, state.text1))
                }
                ToneColorItem(modifier = Modifier.weight(1f),text = state.text2, color = state.color2) {
                    openScreen(Screen.ColorScreen.passSeasonAndTone(season, state.text2))
                }
                ToneColorItem(modifier = Modifier.weight(1f),text = state.text3, color = state.color3) {
                    openScreen(Screen.ColorScreen.passSeasonAndTone(season, state.text3))
                }
            }
        }
    }

}
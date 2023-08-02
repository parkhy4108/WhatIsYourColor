package com.devstitch.whatsyourcolor.presentation.tone

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.navigation.Screen

@Composable
fun ToneScreen(
    season: String,
    openScreen: (String) -> Unit,
    popUp: () -> Unit,
    viewModel: ToneViewModel = hiltViewModel()
) {
    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.init(season)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            state.color1?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(state.color1!!)
                        .clickable {
                            openScreen(
                                Screen.ColorScreen.passSeasonAndTone(
                                    season,
                                    state.text1
                                )
                            )
                        }
                ) {
                    Text(
                        text = state.text1,
                        modifier = Modifier.align(Alignment.Center),
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                    )
                }
            }
            state.color2?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(state.color2!!)
                        .clickable {
                            openScreen(
                                Screen.ColorScreen.passSeasonAndTone(
                                    season,
                                    state.text2
                                )
                            )
                        }
                ) {
                    Text(
                        text = state.text2,
                        modifier = Modifier.align(Alignment.Center),
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
                }
            }
            state.color3?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(state.color3!!)
                        .clickable {
                            openScreen(
                                Screen.ColorScreen.passSeasonAndTone(
                                    season,
                                    state.text3
                                )
                            )
                        }
                ) {
                    Text(
                        text = state.text3,
                        modifier = Modifier.align(Alignment.Center),
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
                }
            }
        }
        IconButton(
            onClick = { popUp() },
            modifier = Modifier.align(Alignment.TopStart).padding(4.dp,0.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.back) , contentDescription = "BackButton", tint = Color.Black)
        }
    }

}
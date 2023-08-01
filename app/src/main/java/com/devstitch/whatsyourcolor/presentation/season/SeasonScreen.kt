package com.devstitch.whatsyourcolor.presentation.season

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstitch.whatsyourcolor.color.Autumn
import com.devstitch.whatsyourcolor.color.Spring
import com.devstitch.whatsyourcolor.color.Summer
import com.devstitch.whatsyourcolor.color.Winter
import com.devstitch.whatsyourcolor.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SeasonScreen(
    openScreen: (String) -> Unit
) {
//    val springColor = Spring().brightTone[1]
//    val summerColor = Summer().lightTone[8]
//    val autumnColor = Autumn().deepTone[1]
//    val winterColor = Winter().trueTone[10]
    val springColor = Spring.brightTone[1]
    val summerColor = Summer.lightTone[8]
    val autumnColor = Autumn.deepTone[1]
    val winterColor = Winter.trueTone[10]


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What's your color?",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold
        )
        Box(
            modifier = Modifier
                .padding(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RectangleShape),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .background(springColor)
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                openScreen(Screen.ToneScreen.passSeason("Spring"))
                            }
                    ) {
                        Text(
                            text = "SPRING",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(10.dp),
                            fontFamily = FontFamily.Cursive
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(summerColor)
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                openScreen(Screen.ToneScreen.passSeason("Summer"))
                            }
                    ) {
                        Text(
                            text = "SUMMER",
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(10.dp),
                            fontFamily = FontFamily.Cursive
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .background(autumnColor)
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                openScreen(Screen.ToneScreen.passSeason("Autumn"))
                            }
                    ) {
                        Text(
                            text = "AUTUMN",
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(10.dp),
                            fontFamily = FontFamily.Cursive
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(winterColor)
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                openScreen(Screen.ToneScreen.passSeason("Winter"))
                            }
                    ) {
                        Text(
                            text = "WINTER",
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(10.dp),
                            fontFamily = FontFamily.Cursive
                        )
                    }
                }
            }
        }
    }
}

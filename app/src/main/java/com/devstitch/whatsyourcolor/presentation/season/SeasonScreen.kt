package com.devstitch.whatsyourcolor.presentation.season

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstitch.whatsyourcolor.R
import com.devstitch.whatsyourcolor.common.navigation.Screen
import com.devstitch.whatsyourcolor.data.Colors
import com.devstitch.whatsyourcolor.presentation.composable.StandardText
import com.devstitch.whatsyourcolor.R.string as AppText

@Composable
fun SeasonScreen(openScreen: (String) -> Unit) {
    val springColor = Colors().springBrightTone[1]
    val summerColor = Colors().summerLightTone[8]
    val autumnColor = Colors().autumnDeepTone[1]
    val winterColor = Colors().winterTrueTone[10]
    val interactionSource = remember { MutableInteractionSource() }

    LazyColumn(
        modifier = Modifier.systemBarsPadding().fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { StandardText(text = AppText.title, fontSize = 30.sp) }
        item {
            Card(
                modifier = Modifier
                    .size(350.dp)
                    .padding(30.dp),
                elevation = CardDefaults.cardElevation(15.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
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
                                .clickable { openScreen(Screen.ToneScreen.passSeason("Spring")) }
                        ) {
                            StandardText(
                                text = AppText.Spring,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(10.dp),
                                fontColor = Color.DarkGray
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(summerColor)
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { openScreen(Screen.ToneScreen.passSeason("Summer")) }
                        ) {
                            StandardText(
                                text = AppText.Summer,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(10.dp),
                                fontColor = Color.DarkGray
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
                                .clickable { openScreen(Screen.ToneScreen.passSeason("Autumn")) }
                        ) {
                            StandardText(
                                text = AppText.Autumn,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(10.dp),
                                fontColor = Color.DarkGray
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(winterColor)
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { openScreen(Screen.ToneScreen.passSeason("Winter")) }
                        ) {
                            StandardText(
                                text = AppText.Winter,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(10.dp),
                                fontColor = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
        item {
            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { openScreen(Screen.MyPaletteScreen.route) }
                    )
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StandardText(text = AppText.My)
                    Icon(
                        painter = painterResource(id = R.drawable.palette),
                        contentDescription = stringResource(id = AppText.myPalette)
                    )
                    StandardText(text = AppText.Palette)
                }
            }
        }
    }

}
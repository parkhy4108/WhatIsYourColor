package com.devstitch.whatsyourcolor.presentation.season

import android.view.ViewGroup
import android.widget.LinearLayout
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.devstitch.whatsyourcolor.color.Colors
import com.devstitch.whatsyourcolor.navigation.Screen
import com.devstitch.whatsyourcolor.presentation.AdView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun SeasonScreen(
    openScreen: (String) -> Unit
) {
    val springColor = Colors().springBrightTone[1]
    val summerColor = Colors().summerLightTone[8]
    val autumnColor = Colors().autumnDeepTone[1]
    val winterColor = Colors().winterTrueTone[10]

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
            modifier = Modifier.padding(30.dp),
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
                            .clickable { openScreen(Screen.ToneScreen.passSeason("Spring")) }
                    ) {
                        Text(
                            text = "SPRING",
                            fontFamily = FontFamily.Cursive,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(10.dp),
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(summerColor)
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { openScreen(Screen.ToneScreen.passSeason("Summer")) }
                    ) {
                        Text(
                            text = "SUMMER",
                            fontFamily = FontFamily.Cursive,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(10.dp)
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

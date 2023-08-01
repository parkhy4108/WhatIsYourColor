package com.devstitch.whatsyourcolor.presentation.tone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devstitch.whatsyourcolor.color.Autumn
import com.devstitch.whatsyourcolor.color.Spring
import com.devstitch.whatsyourcolor.color.Summer
import com.devstitch.whatsyourcolor.color.Winter

@Composable
fun ToneScreen(
    season : String
){
    println("season: $season")
    val springColor = Spring.brightTone[1]
    Box(modifier = Modifier.background(springColor).fillMaxSize()) {

    }
}
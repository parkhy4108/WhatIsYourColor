package com.devstitch.whatsyourcolor.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ToneColorItem(
    modifier: Modifier = Modifier,
    text: String,
    color: Color?,
    openScreen: () -> Unit,
) {
    color?.let {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color)
                .clickable { openScreen() }
        ) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
            )
        }
    }
}
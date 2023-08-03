package com.devstitch.whatsyourcolor.presentation.composable

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun StandardText(
    modifier : Modifier = Modifier,
    @StringRes text: Int,
    fontSize: TextUnit = 20.sp,
    fontColor: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = stringResource(id = text),
        fontSize = fontSize,
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.ExtraBold,
        color = fontColor
    )
}
package com.devstitch.whatsyourcolor.presentation.composable

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.devstitch.whatsyourcolor.R

@Composable
fun StandardIconButton(
    modifier : Modifier = Modifier,
    @DrawableRes painter: Int,
    bgColor : Color = Color.Black,
    enabled : Boolean = true,
    onClick: () -> Unit,
    ) {
    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = { onClick() },
    ) {
        Icon(
            painter = painterResource(id = painter),
            contentDescription = stringResource(id = R.string.iconBtn),
            tint = bgColor
        )
    }

}
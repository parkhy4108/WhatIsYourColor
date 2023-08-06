package com.devstitch.whatsyourcolor.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devstitch.whatsyourcolor.R.string as AppText

@Composable
fun DeleteDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    val boxColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(boxColor)
                .padding(15.dp, 20.dp, 15.dp, 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = AppText.DeleteColors),
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = AppText.DelDialogText),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                maxLines = 1,
                fontSize = 13.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = { onDismiss() },
                    contentPadding = PaddingValues(0.dp),
                    content = {
                        Text(
                            text = stringResource(id = AppText.Cancel),
                            color = Color.Blue
                        )
                    }
                )
                TextButton(
                    onClick = { onConfirm() },
                    contentPadding = PaddingValues(0.dp),
                    enabled = true,
                    content = {
                        Text(
                            text = stringResource(id = AppText.Delete),
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }
    }
}
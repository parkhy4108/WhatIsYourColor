package com.devstitch.whatsyourcolor.presentation

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.devstitch.whatsyourcolor.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun GoogleAdView(
    modifier: Modifier = Modifier
) {
    val width = LocalConfiguration.current.screenWidthDp
    val isInEditMode = LocalInspectionMode.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isInEditMode) {
            Text(
                text = "Ad Here",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.Center),
            )
        } else {
            AndroidView(
                modifier = modifier.fillMaxSize(),
                factory = { context ->
                    AdView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setAdSize(
                            AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, width)
                        )
                        adUnitId = context.getString(R.string.adTest)
                        loadAd(AdRequest.Builder().build())
                    }
                },
                update = { it.loadAd(AdRequest.Builder().build()) }
            )
        }

        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .align(Alignment.BottomStart)
        )
    }
}
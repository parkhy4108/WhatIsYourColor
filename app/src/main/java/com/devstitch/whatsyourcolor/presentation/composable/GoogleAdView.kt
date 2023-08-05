package com.devstitch.whatsyourcolor.presentation.composable

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.devstitch.whatsyourcolor.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun GoogleAdView() {

    val width = LocalConfiguration.current.screenWidthDp
    val isInEditMode = LocalInspectionMode.current
    var adSuccess = false
    val adError = remember { mutableStateOf(false) }
    val adRequest = AdRequest.Builder().build()
    val listener = object : AdListener() {
        override fun onAdFailedToLoad(p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
            adSuccess = false
            if (!adError.value) adError.value = true
        }

        override fun onAdLoaded() {
            super.onAdLoaded()
            if (adError.value) adError.value = false
            adSuccess = true
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isInEditMode || (adError.value && !adSuccess)) {
            StandardText(text = R.string.adHere)
        } else {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    AdView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setAdSize(
                            AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                                context,
                                width
                            )
                        )
                        adUnitId = context.getString(R.string.adId)
                        adListener = listener
                        loadAd(adRequest)
                    }
                },
                update = { it.loadAd(adRequest) }
            )
        }
        Divider(
            color = if(isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .align(Alignment.BottomStart)
        )
    }
}
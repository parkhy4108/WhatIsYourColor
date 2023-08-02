package com.devstitch.whatsyourcolor.presentation

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.devstitch.whatsyourcolor.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize

@Composable
fun AdView(
    modifier: Modifier = Modifier
){
    AndroidView(
        modifier = modifier,
        factory = { context ->
            com.google.android.gms.ads.AdView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                adUnitId = context.getString(R.string.adTest)
                setAdSize(AdSize.BANNER)
                loadAd(AdRequest.Builder().build())
            }
        },
        update = {
            it.loadAd(AdRequest.Builder().build())
        }
    )
}
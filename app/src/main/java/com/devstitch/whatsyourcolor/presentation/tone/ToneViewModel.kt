package com.devstitch.whatsyourcolor.presentation.tone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devstitch.whatsyourcolor.data.Colors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToneViewModel @Inject constructor() : ViewModel() {

    val color = Colors()
    var state = mutableStateOf(ToneState())
        private set
    fun init(season: String) {
        when(season.removeSurrounding("{", "}")) {
            "Spring" -> {
                state.value = state.value.copy(
                    text1 = "Bright",
                    text2 = "True",
                    text3 = "Light",
                    color1 = color.springBrightTone[0],
                    color2 = color.springTrueTone[1],
                    color3 = color.springLightTone[3]
                )
            }
            "Summer" -> {
                state.value = state.value.copy(
                    text1 = "Light",
                    text2 = "True",
                    text3 = "Mute",
                    color1 = color.summerLightTone[8],
                    color2 = color.summerTrueTone[5],
                    color3 = color.summerMuteTone[11]
                )
            }
            "Autumn" -> {
                state.value = state.value.copy(
                    text1 = "Mute",
                    text2 = "True",
                    text3 = "Deep",
                    color1 = color.autumnMuteTone[0],
                    color2 = color.autumnTrueTone[13],
                    color3 = color.autumnDeepTone[12]
                )
            }
            else -> {
                state.value = state.value.copy(
                    text1 = "Bright",
                    text2 = "True",
                    text3 = "Dark",
                    color1 = color.winterBrightTone[0],
                    color2 = color.winterTrueTone[10],
                    color3 = color.winterDarkTone[4]
                )
            }
        }
    }
}
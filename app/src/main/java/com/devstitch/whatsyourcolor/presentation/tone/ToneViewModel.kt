package com.devstitch.whatsyourcolor.presentation.tone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devstitch.whatsyourcolor.color.Colors
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
                    color1 = color.springBrightTone[1],
                    color2 = color.springTrueTone[4],
                    color3 = color.springLightTone[7]
                )
            }
            "Summer" -> {
                state.value = state.value.copy(
                    text1 = "Light",
                    text2 = "True",
                    text3 = "Mute",
                    color1 = color.summerLightTone[10],
                    color2 = color.summerTrueTone[9],
                    color3 = color.summerMuteTone[15]
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
                    color2 = color.winterTrueTone[16],
                    color3 = color.winterDarkTone[4]
                )
            }
        }
    }
}
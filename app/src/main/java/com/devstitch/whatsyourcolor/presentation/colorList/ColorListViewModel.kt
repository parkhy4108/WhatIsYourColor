package com.devstitch.whatsyourcolor.presentation.colorList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstitch.whatsyourcolor.data.Colors
import com.devstitch.whatsyourcolor.data.RepositoryImpl
import com.devstitch.whatsyourcolor.domain.MyColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class ColorListViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {

    var state = mutableStateOf(ColorListState())
        private set

    fun init(season: String, tone: String) {
        val colorList = when (season) {
            "Spring" -> {
                when (tone) {
                    "Bright" -> Colors().springBrightTone
                    "True" -> Colors().springTrueTone
                    else -> Colors().springLightTone
                }
            }

            "Summer" -> {
                when (tone) {
                    "Light" -> Colors().summerLightTone
                    "True" -> Colors().summerTrueTone
                    else -> Colors().summerMuteTone
                }
            }

            "Autumn" -> {
                when (tone) {
                    "Mute" -> Colors().autumnMuteTone
                    "True" -> Colors().autumnTrueTone
                    else -> Colors().autumnDeepTone
                }
            }

            else -> {
                when (tone) {
                    "Bright" -> Colors().winterBrightTone
                    "True" -> Colors().winterTrueTone
                    else -> Colors().winterDarkTone
                }
            }
        }
        state.value = state.value.copy(colorList = colorList)
    }

    fun clickedBox(row: Int, index: Int) {
        onChangedPagerState(row * 4 + index)
        onChangedExpandState()
    }

    private fun onChangedPagerState(newInitial: Int) {
        state.value = state.value.copy(pagerState = PagerState(newInitial))
    }

    fun onChangedExpandState() {
        state.value = state.value.copy(expand = !state.value.expand)
    }

    fun saveColor(color: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val hasColor = repository.hasColors(color)
            if(!hasColor) {
                repository.saveColor(MyColor(colorRGB = color))
            }
        }
    }




}
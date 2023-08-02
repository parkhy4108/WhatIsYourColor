package com.devstitch.whatsyourcolor.presentation.colorList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstitch.whatsyourcolor.color.Colors
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

    var colorListState = mutableStateOf(ColorListState())
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
        colorListState.value = colorListState.value.copy(colorList = colorList)
    }

    fun clickedBox(color: Int, index: Int) {
        onChangedSelectedColor(newColor = color)
        onChangedSelectedIndex(newIndex = index)
        onChangedExpandState()
    }

    private fun onChangedSelectedColor(newColor: Int) {
        colorListState.value = colorListState.value.copy(selectedColor = newColor)
    }

    private fun onChangedSelectedIndex(newIndex: Int) {
        colorListState.value = colorListState.value.copy(selectedIndex = newIndex)
    }

    fun onChangedPagerState(newInitial: Int) {
        colorListState.value = colorListState.value.copy(pagerState = PagerState(newInitial))
    }

    fun onChangedExpandState() {
        colorListState.value = colorListState.value.copy(expand = !colorListState.value.expand)
    }

    fun saveColor(color: Int) {
        viewModelScope.launch(Dispatchers.IO) { repository.saveColor(MyColor(colorRGB = color)) }
    }


}
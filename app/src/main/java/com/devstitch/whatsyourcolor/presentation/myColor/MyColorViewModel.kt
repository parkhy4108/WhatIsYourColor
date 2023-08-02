package com.devstitch.whatsyourcolor.presentation.myColor

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstitch.whatsyourcolor.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyColorViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {

    var state = mutableStateOf(MyColorState())
        private set

    fun init(){
        repository.getAllMyColors().onEach { list ->
            state.value = state.value.copy(colors = list)
        }.launchIn(viewModelScope)
    }

    fun deleteButtonClicked(list: MutableList<Int>) {
        viewModelScope.launch { repository.deleteColors(list) }
    }

}
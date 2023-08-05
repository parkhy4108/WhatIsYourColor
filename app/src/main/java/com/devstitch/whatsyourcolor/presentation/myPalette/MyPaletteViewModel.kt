package com.devstitch.whatsyourcolor.presentation.myPalette

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstitch.whatsyourcolor.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class MyPaletteViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {

    var state = mutableStateOf(MyPaletteState())
        private set

    var mapState = mutableStateMapOf<Int,Boolean>()
        private set
    fun init(){
        repository.getAllMyColors().onEach { list ->
            mapState.clear()
            list.forEach { mapState[it.id] = false }
            state.value = state.value.copy(colorList = list)
        }.launchIn(viewModelScope)
    }

    fun deleteButtonClicked() {
        state.value = state.value.copy(showDialog = true)
    }

    fun cancelDialog(){
        state.value = state.value.copy(showDialog = false)
    }

    fun onChangedCheckedMapState(newCheckedId: Int){
        mapState[newCheckedId] = !mapState[newCheckedId]!!
    }

    fun onDialogConfirmClicked(){
        val deleteList = mutableListOf<Int>()
        mapState.forEach { (id, state) ->
            if(state) deleteList.add(id)
        }
        viewModelScope.launch { repository.deleteColors(deleteList) }
        state.value = state.value.copy(showDialog = false, isListBtnClicked = false)
    }

    fun clickedBox(index: Int) {
        onChangedPagerState(index)
        onChangedExpandState()
    }

    private fun onChangedPagerState(newInitial: Int) {
        state.value = state.value.copy(pagerState = PagerState(newInitial))
    }

    fun onChangedExpandState() {
        state.value = state.value.copy(expand = !state.value.expand)
    }

    fun onChangedCheckedState(){
        state.value = state.value.copy(isListBtnClicked = !state.value.isListBtnClicked)
    }

}
package com.devstitch.whatsyourcolor.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllMyColors() : Flow<List<MyColor>>
    suspend fun saveColor(color: MyColor)
    suspend fun deleteColors(colorList : List<Int>)
    suspend fun hasColors(color: Int) : Boolean
}
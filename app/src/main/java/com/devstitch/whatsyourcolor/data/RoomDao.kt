package com.devstitch.whatsyourcolor.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devstitch.whatsyourcolor.domain.MyColor
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Query("SELECT * FROM MyColors ORDER BY id DESC")
    fun getMyColors(): Flow<List<MyColor>>

    @Upsert
    suspend fun upsertColor(color: MyColor)

    @Query("DELETE FROM MyColors WHERE id in (:idList)")
    suspend fun deleteColors(idList: List<Int>)

    @Query("SELECT EXISTS(SELECT * FROM MyColors WHERE colorRGB = :color)")
    fun hasColors(color: Int) : Boolean

}
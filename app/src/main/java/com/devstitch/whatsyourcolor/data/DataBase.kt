package com.devstitch.whatsyourcolor.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devstitch.whatsyourcolor.domain.MyColor

@Database(
    entities = [MyColor::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    companion object {
        const val DB_NAME = "ColorDB"
    }
    abstract fun roomDao() : RoomDao
}
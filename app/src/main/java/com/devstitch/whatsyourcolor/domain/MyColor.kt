package com.devstitch.whatsyourcolor.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyColors")
data class MyColor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val colorRGB: Int
)

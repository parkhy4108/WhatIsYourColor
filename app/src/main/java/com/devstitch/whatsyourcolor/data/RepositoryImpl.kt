package com.devstitch.whatsyourcolor.data

import com.devstitch.whatsyourcolor.domain.MyColor
import com.devstitch.whatsyourcolor.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val roomDao: RoomDao
) : Repository {
    override fun getAllMyColors(): Flow<List<MyColor>> {
        return roomDao.getMyColors()
    }

    override suspend fun saveColor(color: MyColor) {
        return roomDao.upsertColor(color)
    }

    override suspend fun deleteColors(colorList: List<Int>) {
        return roomDao.deleteColors(colorList)
    }

}
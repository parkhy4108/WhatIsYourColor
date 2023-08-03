package com.devstitch.whatsyourcolor.common.di

import android.app.Application
import androidx.room.Room
import com.devstitch.whatsyourcolor.data.RoomDao
import com.devstitch.whatsyourcolor.data.DataBase
import com.devstitch.whatsyourcolor.data.RepositoryImpl
import com.devstitch.whatsyourcolor.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): DataBase {
        return Room.databaseBuilder(
            app,
            DataBase::class.java,
            DataBase.DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(dataBase: DataBase) = dataBase.roomDao()

    @Singleton
    @Provides
    fun provideRepository(dao: RoomDao): Repository {
        return RepositoryImpl(dao)
    }


}
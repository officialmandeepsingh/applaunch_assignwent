package com.mandeep.applaunchtask.di

import android.content.Context
import androidx.room.Room
import com.mandeep.applaunchtask.data.local.LocalDatabase
import com.mandeep.applaunchtask.data.local.RoomDbConstant
import com.mandeep.applaunchtask.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, RoomDbConstant.TABLE_USER)
            .build()
    }

    @Provides
    fun provideUserDao(localDatabase: LocalDatabase):UserDao{
        return localDatabase.userDao()
    }



}
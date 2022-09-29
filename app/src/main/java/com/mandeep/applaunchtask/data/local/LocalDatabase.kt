package com.mandeep.applaunchtask.data.local

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [User::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
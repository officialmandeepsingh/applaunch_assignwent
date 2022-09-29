package com.mandeep.applaunchtask.data.local

import androidx.annotation.WorkerThread
import androidx.room.*

@Dao
interface UserDao {
    @WorkerThread
    @Insert
    suspend fun insertUserData(user: User):Long

    @WorkerThread
    @Delete
    suspend fun deleteUserData(user: User)

    @WorkerThread
    @Query("SELECT * FROM ${RoomDbConstant.TABLE_USER}")
    suspend fun getAllUserData(): List<User>

}
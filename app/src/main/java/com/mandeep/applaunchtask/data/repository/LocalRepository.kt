package com.mandeep.applaunchtask.data.repository

import com.mandeep.applaunchtask.data.local.User
import com.mandeep.applaunchtask.data.local.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun addUser(user: User): Long {
        return userDao.insertUserData(user)
    }

    suspend fun getAllUserList(): List<User> {
        return userDao.getAllUserData()
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUserData(user)
    }

}
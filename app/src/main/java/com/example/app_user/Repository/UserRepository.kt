package com.example.app_user.Repository

import com.example.app_user.dao.UserDao
import com.example.app_user.model.User

class UserRepository (private  val userDao: UserDao) {


    suspend fun getAllUsersFromRepo(): List<User>{
        return userDao.getAllusers()
    }
    suspend  fun insert(user: User){
        userDao.insert(user)
    }

    suspend fun update(user: User){
        userDao.update(user)
    }
    suspend fun delete(user: User){
        userDao.delete(user)
    }
    suspend fun searchUsersFromRepo(query: String): List<User>{
        return userDao.searchusers(query)
    }



}



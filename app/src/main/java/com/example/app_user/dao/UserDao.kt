package com.example.app_user.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_user.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete//to delete data in the table
    suspend fun delete(user: User)

    //to get data from the table-> select all from table
    @Query("SELECT * FROM users")
    suspend fun getAllusers(): List<User>

    @Query("SELECT * from users where name like '%' || :query || '%'")
    suspend fun searchusers(query: String): List<User>
}
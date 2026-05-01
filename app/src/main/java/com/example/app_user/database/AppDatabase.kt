package com.example.app_user.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_user.dao.UserDao
import com.example.app_user.model.User


@Database(entities = [User:: class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun  userDao(): UserDao
    companion object{
        private  var INSTANCE : AppDatabase?=null
        fun getDatabase(context: Context):AppDatabase{
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "user_db"
                ).allowMainThreadQueries()
                    .build()


            }
            return INSTANCE!!

        }

    }
}
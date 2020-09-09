package com.project.hackathon.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.hackathon.data.Check

@Database(entities = [Check::class], version = 3)
abstract class DataBase : RoomDatabase() {

    abstract  fun dao() : Dao

    companion object{
        private var INSTANCE : DataBase? = null

        fun getInstance(context : Context) : DataBase?{
            if(INSTANCE == null){
                synchronized(Check::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DataBase::class.java, "Check.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}
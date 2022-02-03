package com.codingbhasha.simplesocialmedia.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingbhasha.simplesocialmedia.models.conservations.Friend

@Database(entities = [Friend::class], version = 1, exportSchema = false)
abstract class roomDB : RoomDatabase() {

    abstract fun mDao():DAO companion object{
        @Volatile
        private var INSTANCE: roomDB? = null

        fun getDatabase(context: Context): roomDB {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        roomDB::class.java,
                        "movies_db")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }


}
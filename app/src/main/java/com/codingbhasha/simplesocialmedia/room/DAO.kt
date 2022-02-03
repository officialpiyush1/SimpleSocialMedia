package com.codingbhasha.simplesocialmedia.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingbhasha.simplesocialmedia.models.conservations.Friend

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: List<Friend>)

    @Query("SELECT * FROM conver_table")
    suspend fun getAll(): List<Friend>
}
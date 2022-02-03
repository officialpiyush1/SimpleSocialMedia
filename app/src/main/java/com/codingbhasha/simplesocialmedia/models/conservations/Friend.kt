package com.codingbhasha.simplesocialmedia.models.conservations

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "conver_table" , indices = [Index(value = ["Name"], unique = true)])
data class Friend(
    @PrimaryKey(autoGenerate = true)var rowid: Int,
    val LastMessage: String,
    val Name: String,
    val ProfileImage: String
)
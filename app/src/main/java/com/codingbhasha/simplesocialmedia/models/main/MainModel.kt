package com.codingbhasha.simplesocialmedia.models.main

data class MainModel(
    val FriendList: List<Friend>,
    val Message: String,
    val Name: String,
    val PageNo: Int,
    val Status: Boolean,
    val UserId: Int
)
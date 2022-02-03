package com.codingbhasha.simplesocialmedia.models.conservations

import com.codingbhasha.simplesocialmedia.repository.Repository

data class conservations(
    val FriendList: List<Friend>,
    val Message: String,
    val Status: Boolean
)
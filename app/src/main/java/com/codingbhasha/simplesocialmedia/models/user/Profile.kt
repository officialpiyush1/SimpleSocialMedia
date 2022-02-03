package com.codingbhasha.simplesocialmedia.models.user

data class Profile(
    val Description: String,
    val FollowersCount: Int,
    val FollowsCount: Int,
    val Location: String,
    val Message: String,
    val Name: String,
    val PhotoList: List<Photo>,
    val PhotosCount: Int,
    val Status: Boolean,
    val UserId: Int
)
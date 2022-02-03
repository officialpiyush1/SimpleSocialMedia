package com.codingbhasha.simplesocialmedia.models.post

data class PostData(
    val Message: String,
    val PageNo: Int,
    val PostList: List<Post>,
    val Status: Boolean
)
package com.codingbhasha.simplesocialmedia.viewholders.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codingbhasha.simplesocialmedia.repository.Repository



class PostViewHolderFactory(private val Repo: Repository , private val pageId:Int): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewHolder(Repo,pageId)  as T
    }
}
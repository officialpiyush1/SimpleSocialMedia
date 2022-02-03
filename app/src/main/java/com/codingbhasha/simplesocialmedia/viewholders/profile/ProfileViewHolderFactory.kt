package com.codingbhasha.simplesocialmedia.viewholders.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codingbhasha.simplesocialmedia.repository.Repository
import com.codingbhasha.simplesocialmedia.viewholders.post.PostViewHolder

class ProfileViewHolderFactory(private val Repo: Repository, private val userid:Int): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewHolder(Repo,userid)  as T
    }
}
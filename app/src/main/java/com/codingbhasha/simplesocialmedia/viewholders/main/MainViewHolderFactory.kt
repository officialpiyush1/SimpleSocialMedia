package com.codingbhasha.simplesocialmedia.viewholders.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codingbhasha.simplesocialmedia.repository.Repository



class MainViewHolderFactory(private val Repo: Repository ): ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewHolder(Repo)  as T
    }
}
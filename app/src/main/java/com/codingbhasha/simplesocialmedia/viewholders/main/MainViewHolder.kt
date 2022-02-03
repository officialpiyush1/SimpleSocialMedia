package com.codingbhasha.simplesocialmedia.viewholders.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingbhasha.simplesocialmedia.models.main.MainModel
import com.codingbhasha.simplesocialmedia.repository.Repository
import com.codingbhasha.simplesocialmedia.repository.Responce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
class MainViewHolder(val Repo: Repository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            Repo.getMain()
        }
    }
    val  main : LiveData<Responce<MainModel>>
        get() = Repo.main
}
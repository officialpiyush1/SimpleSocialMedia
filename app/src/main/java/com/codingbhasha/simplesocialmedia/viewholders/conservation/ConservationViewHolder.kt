package com.codingbhasha.simplesocialmedia.viewholders.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingbhasha.simplesocialmedia.models.conservations.conservations
import com.codingbhasha.simplesocialmedia.repository.Repository

import com.codingbhasha.simplesocialmedia.repository.Responce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
class ConservationViewHolder(val Repo: Repository) : ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO){
            Repo.getConversation()
        }
    }
    val  conver : LiveData<Responce<conservations>>
        get() = Repo.getConversationD
}
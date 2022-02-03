package com.codingbhasha.simplesocialmedia.viewholders.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingbhasha.simplesocialmedia.models.user.Profile
import com.codingbhasha.simplesocialmedia.repository.Repository

import com.codingbhasha.simplesocialmedia.repository.Responce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
class ProfileViewHolder(val Repo: Repository, userid: Int): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            Repo.getProfile(userid)
        }
    }
    val  post : LiveData<Responce<Profile>>
        get() = Repo.getProfileData
}


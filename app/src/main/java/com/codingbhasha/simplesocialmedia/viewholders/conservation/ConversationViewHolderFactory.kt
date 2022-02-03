package com.codingbhasha.simplesocialmedia.viewholders.conservation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codingbhasha.simplesocialmedia.models.conservations.conservations
import com.codingbhasha.simplesocialmedia.repository.Repository
import com.codingbhasha.simplesocialmedia.viewholders.profile.ConservationViewHolder


class ConversationViewHolderFactory(private val Repo: Repository): ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConservationViewHolder(Repo)  as T
    }
}
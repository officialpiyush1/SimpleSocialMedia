package com.codingbhasha.simplesocialmedia.viewholders.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingbhasha.simplesocialmedia.models.post.PostData
import com.codingbhasha.simplesocialmedia.repository.Repository
import com.codingbhasha.simplesocialmedia.repository.Responce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
class PostViewHolder(val Repo: Repository, pageId: Int): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            Repo.getPostData()
        }
    }
    val  post : LiveData<Responce<PostData>>
    get() = Repo.PostData

}
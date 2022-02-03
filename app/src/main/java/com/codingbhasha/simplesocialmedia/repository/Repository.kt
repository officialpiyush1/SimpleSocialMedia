package com.codingbhasha.simplesocialmedia.repository

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingbhasha.simplesocialmedia.models.conservations.conservations
import com.codingbhasha.simplesocialmedia.models.main.MainModel
import com.codingbhasha.simplesocialmedia.models.post.PostData
import com.codingbhasha.simplesocialmedia.models.user.Profile
import com.codingbhasha.simplesocialmedia.retrofit.RetrofitApiInterface

import com.codingbhasha.simplesocialmedia.room.roomDB
import com.codingbhasha.simplesocialmedia.util.Utils


class Repository(
    private val api: RetrofitApiInterface,
    val applicationContext: Context,
    roomDB: roomDB
) {


    private val mainLiveData = MutableLiveData<Responce<MainModel>>()
    val main: LiveData<Responce<MainModel>>
        get() = mainLiveData

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getMain(): LiveData<Responce<MainModel>> {
        if (Utils.isOnline(applicationContext)) {
            try {
                val result = api.Main()
                if (result.body()!!.Status) {
                    mainLiveData.postValue(Responce.Success(result.body()!!))
                } else {
                    mainLiveData.postValue(Responce.Error(result.body()!!.Message))
                }
            } catch (e: Exception) {
                mainLiveData.postValue(Responce.Error(e.message.toString()))
            }
        } else {
            mainLiveData.postValue(Responce.Error("No Internet"))
        }
        return mainLiveData
    }


    private val PostDataLiveData = MutableLiveData<Responce<PostData>>()
    val PostData: LiveData<Responce<PostData>>
        get() = PostDataLiveData

    companion object{
        val  pageIndex = 0
    }
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getPostData() {
        if (Utils.isOnline(applicationContext)) {
            try {
                val result = api.PostData(pageIndex)
                if (result.body()!!.Status) {
                    Log.e("adasd",result.body()!!.toString())
                    PostDataLiveData.postValue(Responce.Success(result.body()!!))
                } else {
                    PostDataLiveData.postValue(Responce.Error(result.body()!!.Message))
                }
            } catch (e: Exception) {
                PostDataLiveData.postValue(Responce.Error(e.message.toString()))
            }
        } else {
            PostDataLiveData.postValue(Responce.Error("No Internet"))
        }
    }

    private val ProfileLiveData = MutableLiveData<Responce<Profile>>()
    val getProfileData: LiveData<Responce<Profile>>
        get() = ProfileLiveData

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getProfile(userId: Int) {
        if (Utils.isOnline(applicationContext)) {
            try {
                val result = api.GetProfile(userId)
                if (result.body()!!.Status) {
                    ProfileLiveData.postValue(Responce.Success(result.body()!!))
                } else {
                    ProfileLiveData.postValue(Responce.Error(result.body()!!.Message))
                }
            } catch (e: Exception) {
                ProfileLiveData.postValue(Responce.Error(e.message.toString()))
            }
        } else {
            ProfileLiveData.postValue(Responce.Error("No Internet"))
        }
    }


    private val GetConversationLiveData = MutableLiveData<Responce<conservations>>()
    val getConversationD: LiveData<Responce<conservations>>
        get() = GetConversationLiveData

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getConversation() {
        if (Utils.isOnline(applicationContext)) {
            try {
                val result = api.GetConversation()
                if (result.body()!!.Status) {

                    roomDB.getDatabase(applicationContext).mDao().insert(result.body()!!.FriendList)

                    GetConversationLiveData.postValue(Responce.Success(result.body()!!))
                } else {
                    GetConversationLiveData.postValue(Responce.Error(result.body()!!.Message))
                }
            } catch (e: Exception) {
                GetConversationLiveData.postValue(Responce.Error(e.message.toString()))
            }
        } else {

            try {
                val s = roomDB.getDatabase(applicationContext).mDao().getAll()
                val  con = conservations(s,"done",true)
                GetConversationLiveData.postValue(Responce.Success(con))
            } catch (e: Exception) {
                GetConversationLiveData.postValue(Responce.Error(e.message.toString()))
            }



        }
    }


}

package com.codingbhasha.simplesocialmedia.retrofit

import com.codingbhasha.simplesocialmedia.models.conservations.conservations
import com.codingbhasha.simplesocialmedia.models.main.MainModel
import com.codingbhasha.simplesocialmedia.models.post.PostData
import com.codingbhasha.simplesocialmedia.models.user.Profile
import com.codingbhasha.simplesocialmedia.util.Utils
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface RetrofitApiInterface {
    @Headers("Accept: application/json","Token: 123" ,"AppVersion: 100")
    @POST("main")
    suspend fun Main(): Response<MainModel>

    @Headers("Accept: application/json","Token: 123" ,"AppVersion: 100",)
    @POST("PostData")
    suspend fun PostData(@Query("PageNo") PageNo: Int): Response<PostData>

    @Headers("Accept: application/json","Token: 123" ,"AppVersion: 100",)
    @POST("Getprofile")
    suspend fun GetProfile(@Query("UserId") UserId: Int): Response<Profile>

    @Headers("Accept: application/json","Token: 123" ,"AppVersion: 100")
    @POST("GetConversation")
    suspend fun GetConversation(): Response<conservations>

    companion object {
        fun create() : RetrofitApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Utils.apiURL)
                .build()
            return retrofit.create(RetrofitApiInterface::class.java)
        }
    }

}
package com.codingbhasha.simplesocialmedia.util

import android.app.Application
import com.codingbhasha.simplesocialmedia.repository.Repository
import com.codingbhasha.simplesocialmedia.retrofit.RetrofitApiInterface
import com.codingbhasha.simplesocialmedia.room.roomDB


class App: Application() {
    lateinit var repos: Repository
    override fun onCreate() {
        super.onCreate()
        inti()
    }

    private fun inti() {
        val roomDB =  roomDB.getDatabase(applicationContext)
        val api =  RetrofitApiInterface.create()
        repos =  Repository(api,applicationContext,roomDB)
    }
}
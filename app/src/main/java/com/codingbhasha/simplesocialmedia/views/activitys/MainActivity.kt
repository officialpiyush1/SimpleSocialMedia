package com.codingbhasha.simplesocialmedia.views.activitys

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingbhasha.simplesocialmedia.R
import com.codingbhasha.simplesocialmedia.adapters.recylerviews.MainFriendAdapter
import com.codingbhasha.simplesocialmedia.adapters.recylerviews.MainPostAdapter
import com.codingbhasha.simplesocialmedia.base.BaseActivity
import com.codingbhasha.simplesocialmedia.databinding.ActivityMainBinding
import com.codingbhasha.simplesocialmedia.repository.Repository
import com.codingbhasha.simplesocialmedia.repository.Responce
import com.codingbhasha.simplesocialmedia.util.App
import com.codingbhasha.simplesocialmedia.viewholders.main.MainViewHolder
import com.codingbhasha.simplesocialmedia.viewholders.main.MainViewHolderFactory
import com.codingbhasha.simplesocialmedia.viewholders.post.PostViewHolder
import com.codingbhasha.simplesocialmedia.viewholders.post.PostViewHolderFactory


class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mainViewHolder: MainViewHolder

    private lateinit var postViewHolder: PostViewHolder



    private var mAdapter: MainFriendAdapter? = null


    private var mAdapterPost: MainPostAdapter? = null

    var pageIndex = Repository.Companion.pageIndex

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = (application as App).repos

dataBinding!!.imgProfile.setOnClickListener { startActivity(Intent(this,ProfileActivity::class.java ))
    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
}


        mAdapter = MainFriendAdapter()

        dataBinding!!.recyclerView.setHasFixedSize(true)

        dataBinding!!.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        dataBinding!!.recyclerView.adapter = mAdapter


        mAdapterPost = MainPostAdapter()

        dataBinding!!.recyclerViewPost.setHasFixedSize(true)

        dataBinding!!.recyclerViewPost.layoutManager = LinearLayoutManager(this)
        dataBinding!!.recyclerViewPost.adapter = mAdapterPost

        postViewHolder = ViewModelProvider(this,PostViewHolderFactory(repo,pageIndex))[PostViewHolder::class.java]

        postViewHolder.post.observe(this){
            when(it){
                is Responce.Loading -> {}
                is Responce.Success -> {
                    it.data?.let {it1 ->
                            mAdapterPost!!.setList(it1.PostList)
                        }
                }
                is Responce.Error -> {
                    it.error?.let { ita-> Toast.makeText(this,ita, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        mainViewHolder = ViewModelProvider(this, MainViewHolderFactory(repo))[MainViewHolder::class.java]
        mainViewHolder.main.observe(this){
            when(it){
                is Responce.Loading -> {}
                is Responce.Success -> {
                    it.data?.let {


                        it1 ->
                        dataBinding!!.main =  it1
                        mAdapter!!.setList(it1.FriendList)
                        Log.e("adada",it1.Name)}
                }
                is Responce.Error -> {
                    it.error?.let { ita-> Toast.makeText(this,ita, Toast.LENGTH_LONG).show() }
                }
            }
        }

    }

    override val layoutResId: Int
        get() = R.layout.activity_main
}
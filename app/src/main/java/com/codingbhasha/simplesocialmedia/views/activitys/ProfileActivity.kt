package com.codingbhasha.simplesocialmedia.views.activitys

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.codingbhasha.simplesocialmedia.R
import com.codingbhasha.simplesocialmedia.adapters.recylerviews.PhotosAdapter
import com.codingbhasha.simplesocialmedia.base.BaseActivity
import com.codingbhasha.simplesocialmedia.databinding.ActivityProfileBinding
import com.codingbhasha.simplesocialmedia.repository.Responce
import com.codingbhasha.simplesocialmedia.util.App
import com.codingbhasha.simplesocialmedia.viewholders.profile.ProfileViewHolder
import com.codingbhasha.simplesocialmedia.viewholders.profile.ProfileViewHolderFactory

class ProfileActivity : BaseActivity<ActivityProfileBinding>() {

    private lateinit var viewHolder: ProfileViewHolder
    private var mAdapter: PhotosAdapter? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding!!.imgChat.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ConservationActivity::class.java
                )
            )
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        dataBinding!!.back.setOnClickListener {
            finish()
            this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out );
        }
        mAdapter = PhotosAdapter(application)
        dataBinding!!.recyclerView.setHasFixedSize(true)
        val layoutmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutmanager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        dataBinding!!.recyclerView.layoutManager = layoutmanager
        dataBinding!!.recyclerView.adapter = mAdapter
        val repo = (application as App).repos

        viewHolder = ViewModelProvider(
            this,
            ProfileViewHolderFactory(repo, 0)
        ).get(ProfileViewHolder::class.java)

        viewHolder.post.observe(this) {
            when (it) {
                is Responce.Loading -> {}
                is Responce.Success -> {
                    it.data?.let { it1 ->
                        dataBinding!!.profile = it1
                        mAdapter!!.setList(it1.PhotoList)
                    }
                }
                is Responce.Error -> {
                    it.error?.let { ita -> Toast.makeText(this, ita, Toast.LENGTH_LONG).show() }
                }
            }

        }


    }

    override val layoutResId: Int
        get() = R.layout.activity_profile
}
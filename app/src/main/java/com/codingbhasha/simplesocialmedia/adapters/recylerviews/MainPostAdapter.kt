package com.codingbhasha.simplesocialmedia.adapters.recylerviews

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codingbhasha.simplesocialmedia.R
import com.codingbhasha.simplesocialmedia.databinding.MainFriendPicBinding
import com.codingbhasha.simplesocialmedia.databinding.SinglePostBinding
import com.codingbhasha.simplesocialmedia.models.main.Friend
import com.codingbhasha.simplesocialmedia.models.post.Post
import com.squareup.picasso.Picasso

class MainPostAdapter() : RecyclerView.Adapter<MainPostViewHolder>() {
    private var list: ArrayList<Post> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPostViewHolder {
        val binding: SinglePostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.single_post,
            parent,
            false
        )
        return MainPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainPostViewHolder, position: Int) {
        val singleItem = list.get(position)
        Picasso.get()
            .load(singleItem.ProfileImage)
            .into(holder.binding.imgProfile)

        Picasso.get()
            .load(singleItem.PostImage)
            .into(holder.binding.imgPost)

        holder.binding.post =  singleItem

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: List<Post>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }


}

class MainPostViewHolder(var binding: SinglePostBinding) : RecyclerView.ViewHolder(
    binding.root
)
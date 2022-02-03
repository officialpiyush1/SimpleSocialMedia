package com.codingbhasha.simplesocialmedia.adapters.recylerviews

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codingbhasha.simplesocialmedia.R
import com.codingbhasha.simplesocialmedia.databinding.MainFriendPicBinding
import com.codingbhasha.simplesocialmedia.models.main.Friend
import com.squareup.picasso.Picasso



class MainFriendAdapter() : RecyclerView.Adapter<MainFriendPhotoViewHolder>() {
    private var list: List<Friend> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFriendPhotoViewHolder {
        val binding: MainFriendPicBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.main_friend_pic,
            parent,
            false
        )
        return MainFriendPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFriendPhotoViewHolder, position: Int) {
        val singleItem = list.get(position)
        Picasso.get()

            .load(singleItem.ProfileImage)
            .into(holder.binding.imgProfile)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: List<Friend>) {
        this.list = list
        notifyDataSetChanged()
    }


}

class MainFriendPhotoViewHolder(var binding: MainFriendPicBinding) : RecyclerView.ViewHolder(
    binding.root
)
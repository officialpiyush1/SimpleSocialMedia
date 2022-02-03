package com.codingbhasha.simplesocialmedia.adapters.recylerviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingbhasha.simplesocialmedia.R
import com.codingbhasha.simplesocialmedia.databinding.MyprofilePhotoBinding
import com.codingbhasha.simplesocialmedia.models.user.Photo
import com.squareup.picasso.Picasso
import java.net.URL
import kotlin.coroutines.suspendCoroutine


class PhotosAdapter(private var ctx: Context) : RecyclerView.Adapter<PhotoViewHolder>() {
    private var list: List<Photo> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding: MyprofilePhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.myprofile_photo,
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val singleItem = list.get(position)
        Picasso.get()
            .load(singleItem.Image)
            .into(holder.binding.imgPhoto);


    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setList1")
    fun setList(list: List<Photo>) {
        this.list = list
        notifyDataSetChanged()
    }


}

class PhotoViewHolder(var binding: MyprofilePhotoBinding) : RecyclerView.ViewHolder(
    binding.root
)
package com.codingbhasha.simplesocialmedia.adapters.recylerviews

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.codingbhasha.simplesocialmedia.R
import com.codingbhasha.simplesocialmedia.databinding.ConservationsBinding
import com.codingbhasha.simplesocialmedia.models.conservations.Friend
import java.util.*
import kotlin.collections.ArrayList

class ConversationsAdapter(private var ctx: Context) : RecyclerView.Adapter<ConversationViewHolder>() {



     var list: List<Friend> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding: ConservationsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.conservations,
            parent,
            false
        )
        return ConversationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val singleItem = list.get(position)

            Glide.with(ctx.applicationContext)
                .load(singleItem.ProfileImage)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(holder.binding.imgProfile)

        holder.binding.friend = singleItem
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

class ConversationViewHolder(var binding: ConservationsBinding) : RecyclerView.ViewHolder(
    binding.root
)
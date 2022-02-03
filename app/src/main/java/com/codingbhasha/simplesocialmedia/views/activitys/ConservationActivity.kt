package com.codingbhasha.simplesocialmedia.views.activitys

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingbhasha.simplesocialmedia.R
import com.codingbhasha.simplesocialmedia.adapters.recylerviews.ConversationsAdapter
import com.codingbhasha.simplesocialmedia.base.BaseActivity
import com.codingbhasha.simplesocialmedia.databinding.ActivityConservationBinding
import com.codingbhasha.simplesocialmedia.models.conservations.Friend
import com.codingbhasha.simplesocialmedia.repository.Responce
import com.codingbhasha.simplesocialmedia.util.App
import com.codingbhasha.simplesocialmedia.viewholders.conservation.ConversationViewHolderFactory
import com.codingbhasha.simplesocialmedia.viewholders.profile.ConservationViewHolder

class ConservationActivity : BaseActivity<ActivityConservationBinding>() {
    private lateinit var viewHolder: ConservationViewHolder
    private var mAdapter: ConversationsAdapter? = null
    private val s: ArrayList<Friend> = arrayListOf()
    private val search: ArrayList<Friend> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding!!.cardSearch.setOnClickListener { dataBinding!!.editSearch.requestFocus() }


        dataBinding!!.back.setOnClickListener {

            finish()
            this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out );
        }


        mAdapter = ConversationsAdapter(application)
        dataBinding!!.recyclerView.setHasFixedSize(true)
        dataBinding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        dataBinding!!.recyclerView.adapter = mAdapter
        val repo = (application as App).repos

        dataBinding!!.editSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            val `in` = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            `in`.hideSoftInputFromWindow(dataBinding!!.editSearch.getWindowToken(), 0)
            dataBinding!!.editSearch.clearFocus()
            s.clear()
            false
        })
        dataBinding!!.editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(sss: Editable) {
                s.clear()

                search.forEach {
                    if (it.Name.lowercase().contains(sss.toString().lowercase())) {
                        s.add(it)
                    }
                }.let { mAdapter!!.setList(s) }
            }
        })


        viewHolder = ViewModelProvider(
            this,
            ConversationViewHolderFactory(repo)
        ).get(ConservationViewHolder::class.java)

        viewHolder.conver.observe(this) {
            when (it) {
                is Responce.Loading -> {}
                is Responce.Success -> {
                    it.data?.let { it1 ->
                        search.addAll(it1.FriendList)
                        s.addAll(it1.FriendList)

                        mAdapter!!.setList(s)
                    }
                }
                is Responce.Error -> {
                    it.error?.let { ita -> Toast.makeText(this, ita, Toast.LENGTH_LONG).show() }
                }
            }

        }


    }

    override val layoutResId: Int
        get() = R.layout.activity_conservation
}



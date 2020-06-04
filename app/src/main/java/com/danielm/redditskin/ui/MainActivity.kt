package com.danielm.redditskin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielm.redditskin.R
import com.danielm.redditskin.adapters.PostAdapter
import com.danielm.redditskin.mvvm.PostsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel : PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        postViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        postViewModel.posts().observe(this, Observer {
            rvPosts.adapter = PostAdapter(it)
        })
    }
}

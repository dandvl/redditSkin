package com.danielm.redditskin.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielm.redditskin.R
import com.danielm.redditskin.adapters.PostAdapter
import com.danielm.redditskin.mvvm.PostsViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private val postViewModel: PostsViewModel by viewModel()
    private lateinit var postAdapter : PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postAdapter = PostAdapter()

        rvPosts.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
        }

        postViewModel.postsLD.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                val postsResponse = response.body()?.data?.children
                postsResponse?.let { posts ->
                    postAdapter.differ.submitList(posts)
                }
            }
        })

    }
}
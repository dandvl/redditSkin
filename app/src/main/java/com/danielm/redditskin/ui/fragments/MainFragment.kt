package com.danielm.redditskin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.danielm.redditskin.adapters.PostAdapter
import com.danielm.redditskin.databinding.FragmentMainBinding
import com.danielm.redditskin.mvvm.PostsViewModel
import com.danielm.redditskin.utils.Resource
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val postViewModel: PostsViewModel by viewModel()
    private var postAdapter = PostAdapter()
    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPosts.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
        }

        postViewModel.postsLD.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.data?.children?.let { posts ->
                        postAdapter.submitList(posts)
                    }
                    swipeToRefresh.isRefreshing = false
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")
                    }
                    swipeToRefresh.isRefreshing = false
                }
                is Resource.Loading -> {
                }
            }
        })

        swipeToRefresh.setOnRefreshListener(
            OnRefreshListener { postViewModel.loadPosts() }
        )

        rvPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)) {
                    val after = (recyclerView.adapter as PostAdapter).differ.currentList.last().data.name
                    postViewModel.loadPosts(after)
                }
            }
        })

    }

}
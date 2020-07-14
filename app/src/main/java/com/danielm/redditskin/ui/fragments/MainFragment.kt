package com.danielm.redditskin.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        val dynamicLayoutManager = if (isPortrait) LinearLayoutManager(context) else GridLayoutManager(context, 2)

        rvPosts.apply {
            layoutManager = dynamicLayoutManager
            adapter = postAdapter
            itemAnimator = DefaultItemAnimator()
        }

        postViewModel.postsLD.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.data?.children?.let { posts ->
                        postAdapter.submitList(postAdapter.currentList + posts)
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
                    swipeToRefresh.isRefreshing = true
                }
            }
        })

        swipeToRefresh.setOnRefreshListener {
            postAdapter.submitList(emptyList())
            postViewModel.loadPosts()
        }

        rvPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)) {
                    val after = (recyclerView.adapter as PostAdapter).currentList.last().data.name
                    postViewModel.loadPosts(after)
                }
            }
        })

    }

}
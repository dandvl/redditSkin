package com.danielm.redditskin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.danielm.redditskin.adapters.PostAdapter
import com.danielm.redditskin.databinding.FragmentMainBinding
import com.danielm.redditskin.mvvm.PostsViewModel
import com.danielm.redditskin.utils.Resource
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val postViewModel: PostsViewModel by viewModel()
    private lateinit var postAdapter : PostAdapter
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

        postAdapter = PostAdapter()

        rvPosts.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
        }

        postViewModel.postsLD.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    binding.isLoading = false
                    response.data?.data?.children?.let { posts ->
                        postAdapter.submitList(posts)
                    }
                    swipeToRefresh.isRefreshing = false
                }
                is Resource.Error -> {
//                    binding.isLoading = false
                    response.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")
                    }
                    swipeToRefresh.isRefreshing = false
                }
                is Resource.Loading -> {
//                    binding.isLoading = true
                }
            }
        })

        swipeToRefresh.setOnRefreshListener(
            OnRefreshListener { postViewModel.loadPosts() }
        )

    }

}
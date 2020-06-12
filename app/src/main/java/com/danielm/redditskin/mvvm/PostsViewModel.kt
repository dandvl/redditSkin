package com.danielm.redditskin.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielm.redditskin.data.RedditResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(private val postRepository : IRepository) : ViewModel() {

    val postsLD : MutableLiveData<Response<RedditResponse>> = MutableLiveData()

    init {
        loadPosts()
    }

    private fun loadPosts() = viewModelScope.launch {
        postsLD.value = postRepository.posts()
    }

}
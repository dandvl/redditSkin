package com.danielm.redditskin.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielm.redditskin.data.RedditResponse
import com.danielm.redditskin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(private val postRepository : IRepository) : ViewModel() {

    val postsLD : MutableLiveData<Resource<RedditResponse>> = MutableLiveData()

    init {
        loadPosts()
    }

    fun loadPosts() = viewModelScope.launch {
        postsLD.postValue(Resource.Loading())
        val response = postRepository.posts()
        postsLD.postValue(handleBreakingNewsResponse(response))
    }


    private fun handleBreakingNewsResponse(response: Response<RedditResponse>) : Resource<RedditResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}

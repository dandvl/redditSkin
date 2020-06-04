package com.danielm.redditskin.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.danielm.redditskin.api.WebServices
import com.danielm.redditskin.data.PostItem
import com.danielm.redditskin.data.RedditResponse
import retrofit2.Call
import retrofit2.Response

class Repository {

    fun posts(): LiveData<List<PostItem>> {

        var ldPosts = MutableLiveData<List<PostItem>>()

        WebServices.all.top("all", 10).enqueue(object : retrofit2.Callback<RedditResponse> {
            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                Log.e("RMC", "error ${t.message}!")
            }
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                if(response.isSuccessful){
                    var posts = response.body()
                    if(!posts?.data?.children.isNullOrEmpty()){
                        ldPosts.value = posts?.data?.children
                    }else{
                        Log.i("RMC", "no posts!")
                    }
                }else{
                    Log.e("RMC", "response not successful!")
                }
            }
        })

        return ldPosts
    }
}
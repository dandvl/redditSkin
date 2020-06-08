package com.danielm.redditskin.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.danielm.redditskin.api.WebServices
import com.danielm.redditskin.data.PostItem
import com.danielm.redditskin.data.RedditResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.await

class Repository {

    fun posts(): LiveData<List<PostItem>> {

        var ldPosts = MutableLiveData<List<PostItem>>()

        GlobalScope.launch(Dispatchers.IO){
            val posts = WebServices.all.top("all", 10).await()
            withContext(Dispatchers.Main){
                if(!posts.data.children.isNullOrEmpty()){
                    ldPosts.value = posts.data.children
                }else{
                    Log.i("RMC", "no posts!")
                }
            }
        }

        return ldPosts
    }

}
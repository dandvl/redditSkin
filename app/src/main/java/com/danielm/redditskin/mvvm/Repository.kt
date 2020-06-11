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

class Repository  {

    suspend fun posts() = WebServices.all.top("all", 10)

}
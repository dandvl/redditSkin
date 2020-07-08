package com.danielm.redditskin.mvvm

import com.danielm.redditskin.api.WebServices
import com.danielm.redditskin.data.RedditResponse
import retrofit2.Response

class Repository : IRepository  {

    override suspend fun posts(after :String) : Response<RedditResponse> {
        return if(after.isNullOrEmpty()){
            WebServices.all.top("all", 10)
        }else{
            WebServices.all.top("all", 10, after)
        }
    }

}
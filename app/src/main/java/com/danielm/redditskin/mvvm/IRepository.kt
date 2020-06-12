package com.danielm.redditskin.mvvm

import com.danielm.redditskin.data.RedditResponse
import retrofit2.Response

interface IRepository {

    suspend fun posts(): Response<RedditResponse>

}
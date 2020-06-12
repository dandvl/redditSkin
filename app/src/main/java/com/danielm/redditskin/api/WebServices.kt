package com.danielm.redditskin.api

import com.danielm.redditskin.data.RedditResponse
import com.danielm.redditskin.utils.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RedditServices {
    @GET("/r/{subreddit}/hot.json")
    suspend fun top(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int): Response<RedditResponse>
}

object WebServices {
    val all : RedditServices by lazy { retrofit.create(RedditServices::class.java) }
}
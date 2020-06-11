package com.danielm.redditskin.mvvm

import androidx.lifecycle.LiveData
import com.danielm.redditskin.data.PostItem

interface IRepository {

    suspend fun posts(): LiveData<List<PostItem>>

}
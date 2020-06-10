package com.danielm.redditskin.mvvm

import androidx.lifecycle.LiveData
import com.danielm.redditskin.data.PostItem

interface IRepository {

    fun posts(): LiveData<List<PostItem>>

}
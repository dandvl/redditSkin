package com.danielm.redditskin.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.danielm.redditskin.data.PostItem

class PostsViewModel : ViewModel() {

    fun posts () : LiveData<List<PostItem>> =
        Repository().posts()

}
package com.danielm.redditskin.adapters

import androidx.recyclerview.widget.DiffUtil
import com.danielm.redditskin.data.PostItem

class PostItemDiffUtil : DiffUtil.ItemCallback<PostItem>() {

    //TODO Fix repeated content
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem) =
        oldItem.data.id == newItem.data.id

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem) =
        oldItem.data.title == newItem.data.title

}
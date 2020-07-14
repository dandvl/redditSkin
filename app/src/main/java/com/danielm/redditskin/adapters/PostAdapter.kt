package com.danielm.redditskin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielm.redditskin.R
import com.danielm.redditskin.data.PostItem
import com.danielm.redditskin.databinding.PostItemBinding

class PostAdapter : ListAdapter<PostItem, PostAdapter.ViewHolder>(PostItemDiffUtil())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item,
            parent,
            false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingPost.post = getItem(position).data
    }

    class ViewHolder(var bindingPost: PostItemBinding) : RecyclerView.ViewHolder(bindingPost.root)
}
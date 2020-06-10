package com.danielm.redditskin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.danielm.redditskin.R
import com.danielm.redditskin.data.PostItem
import com.danielm.redditskin.databinding.PostItemBinding

class PostAdapter(private val list : List<PostItem>) : RecyclerView.Adapter<PostAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item,
            parent,
            false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingPost.post = list [position].data
    }

    inner class ViewHolder(var bindingPost: PostItemBinding) : RecyclerView.ViewHolder(bindingPost.root)

}
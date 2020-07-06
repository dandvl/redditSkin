package com.danielm.redditskin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.danielm.redditskin.R
import com.danielm.redditskin.data.PostItem
import com.danielm.redditskin.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item,
            parent,
            false)
        )

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingPost.post = differ.currentList[position].data
    }

    class ViewHolder(var bindingPost: PostItemBinding) : RecyclerView.ViewHolder(bindingPost.root)

    private val differCallback = (object : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem) =
             oldItem.data.id == newItem.data.id

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem) =
             oldItem == newItem
    })

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list :List<PostItem>){
        differ.submitList(list)
    }
}
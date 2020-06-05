package com.danielm.redditskin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielm.redditskin.R
import com.danielm.redditskin.data.PostItem

class PostAdapter(private val list : List<PostItem>) : RecyclerView.Adapter<PostAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
       ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position].data
        holder.tvAuthor.text = item.author
        holder.tvTitle.text = item.title
        holder.tvScore.text = item.score.toString()

        Glide.with(holder.tvTitle.context).load(item.thumbnail)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)
    }

    class ViewHolder(parent_view: View) : RecyclerView.ViewHolder(parent_view) {
        val tvAuthor: TextView = parent_view.findViewById(R.id.author)
        val tvTitle: TextView = parent_view.findViewById(R.id.title)
        val tvScore: TextView = parent_view.findViewById(R.id.score)
        val image: ImageView = parent_view.findViewById(R.id.thumbnail)
    }

}
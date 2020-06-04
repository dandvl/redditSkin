package com.danielm.redditskin.adapters

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danielm.redditskin.data.PostItem

class PostAdapter(private val list : List<PostItem>) : RecyclerView.Adapter<PostAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
       ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item, parent, false))


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title_txt.text = item.data.title
    }

    class ViewHolder(parent_view: View) : RecyclerView.ViewHolder(parent_view) {
        val title_txt: TextView = parent_view.findViewById(R.id.text1)
    }

}
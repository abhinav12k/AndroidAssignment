package com.baymax.socialfeed.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baymax.socialfeed.R
import com.baymax.socialfeed.data.entity.PostData

/**
 * Created by abhinav on 18/11/21.
 */

class PostAdapter :
    ListAdapter<PostData, PostAdapter.PostViewHolder>(
        PostDiffCallback()
    ) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val body: TextView = itemView.findViewById(R.id.tvBody)
        fun onBind(postData: PostData) {
            title.text = postData.title
            body.text = postData.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class PostDiffCallback : DiffUtil.ItemCallback<PostData>() {
        override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem.title == newItem.title && oldItem.body == newItem.body
        }

    }
}
package com.example.practicaretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.databinding.PostItemLayoutBinding
import com.example.practicaretrofit.models.Post

class PostListAdapter(private var postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            PostItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PostViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post)
    }

    fun updateData(newPostList: List<Post>?) {
        this.postList.clear()
        newPostList?.let { this.postList.addAll(it) }
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post) {
            val binding = PostItemLayoutBinding.bind(itemView)
            binding.apply {
                lblPostTitle.setText(post.title)
            }
        }
    }
}
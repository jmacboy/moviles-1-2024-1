package com.example.practico1solucion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico1solucion.R

class ImageAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<ImageAdapter.StoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return StoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(images[position])
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgStory: ImageView = itemView.findViewById(R.id.imgStory)

        fun bind(imageId: Int) {
            imgStory.setImageResource(imageId)
        }
    }
}
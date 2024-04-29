package com.example.practico2solucion.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practico2solucion.databinding.IngredientListItemBinding
import com.example.practico2solucion.models.Ingredient

class IngredientsAdapter(
    var ingredients: ArrayList<Ingredient>,
    var listener: OnIngredientClickListener
) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val binding =
            IngredientListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return IngredientViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient, listener)

    }

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ingredient: Ingredient, listener: OnIngredientClickListener) {
            IngredientListItemBinding.bind(itemView).apply {
                lblIngredientName.text = ingredient.name
                Glide.with(itemView.context)
                    .load(ingredient.imagenUrl)
                    .into(imgIngredient);
                root.setOnClickListener {
                    listener.onIngredientClick(ingredient)
                }
                if (ingredient.selected) {
                    viewSelectedIngredient.setBackgroundColor(Color.parseColor("#66FF0000"))
                } else {
                    viewSelectedIngredient.setBackgroundColor(Color.parseColor("#66050505"))
                }
            }
        }
    }

    interface OnIngredientClickListener {
        fun onIngredientClick(ingredient: Ingredient)
    }
}
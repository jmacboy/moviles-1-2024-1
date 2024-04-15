package com.example.practicarecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicarecyclerview.databinding.PersonListItemBinding
import com.example.practicarecyclerview.models.Person

class PersonAdapter(
    private val persons: ArrayList<Person>,
    private val listener: OnPersonClickListener
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding =
            PersonListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PersonViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        holder.bind(person, listener)
    }

    fun clear() {
        persons.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: ArrayList<Person>) {
        persons.addAll(list)
        notifyDataSetChanged()
    }

    class PersonViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person, listener: OnPersonClickListener) {
            val binding = PersonListItemBinding.bind(itemView)
            binding.lblPersonName.text = person.name
            binding.lblPersonPhone.text = person.phone
            Glide.with(itemView.context)
                .load(person.profilePicture)
                .into(binding.imgProfilePicture);
            binding.lblPersonPhone.setOnClickListener {
                listener.onPersonClick(person)
            }
            binding.lblPersonName.setOnClickListener {
                listener.onPersonClick(person)
            }
            binding.btnDeleteItem.setOnClickListener {
                listener.onPersonDeleteClick(person)
            }
        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(person: Person)
        fun onPersonDeleteClick(person: Person)
    }
}



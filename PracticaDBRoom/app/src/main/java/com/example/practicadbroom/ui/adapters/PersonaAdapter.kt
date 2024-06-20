package com.example.practicadbroom.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicadbroom.databinding.PersonaListItemBinding
import com.example.practicadbroom.models.Persona

class PersonaAdapter(
    val personaList: ArrayList<Persona>,
    val listener: OnPersonaClickListener
) :
    RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {

        val binding =
            PersonaListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PersonaViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personaList[position]
        holder.bind(persona, listener)
    }

    fun updateData(personas: java.util.ArrayList<Persona>) {
        this.personaList.clear()
        this.personaList.addAll(personas)
        notifyDataSetChanged()
    }


    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(persona: Persona, listener: OnPersonaClickListener) {
            val binding = PersonaListItemBinding.bind(itemView)
            binding.apply {
                lblPersonId.text = persona.id.toString()
                lblPersonName.text = "${persona.nombre} ${persona.apellido}"
                itemView.setOnClickListener { listener.onPersonaClick(persona) }
                btnPersonDelete.setOnClickListener { listener.onPersonaDelete(persona) }
            }
        }
    }

    interface OnPersonaClickListener {
        fun onPersonaClick(persona: Persona)
        fun onPersonaDelete(persona: Persona)
    }
}
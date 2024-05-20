package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.databinding.PersonaItemLayoutBinding
import com.example.practicaapipersonas.models.Persona
import com.example.practicaapipersonas.models.Personas
import java.util.ArrayList

class PersonaAdapter(val personaList: Personas) :
    RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding =
            PersonaItemLayoutBinding.inflate(
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
        holder.bind(persona)
    }

    fun updateData(personaList: Personas) {
        this.personaList.clear()
        this.personaList.addAll(personaList)
        notifyDataSetChanged()
    }

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(persona: Persona) {
            val binding = PersonaItemLayoutBinding.bind(itemView)
            binding.apply {
                lblPersonaFullName.text = "${persona.nombres} ${persona.apellidos}"
                lblPersonaCity.text = persona.ciudad
            }
        }
    }
}
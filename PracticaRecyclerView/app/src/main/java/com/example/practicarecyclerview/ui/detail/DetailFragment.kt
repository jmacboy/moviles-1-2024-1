package com.example.practicarecyclerview.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practicarecyclerview.databinding.FragmentDetailBinding
import com.example.practicarecyclerview.models.Person

class DetailFragment() : Fragment() {
    var personId: Int? = null

    companion object {
        fun newInstance(person: Person): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putSerializable("person", person)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun loadPersonDetails(person: Person) {
        binding.txtPersonName.setText(person.name)
        binding.txtLastName.setText(person.lastName)
        binding.txtPersonAge.setText(person.age.toString())
        binding.txtPersonEmail.setText(person.email)
        binding.txtPersonPhone.setText(person.phone)
        this.personId = person.id
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupEventListeners()
        val person = arguments?.getSerializable("person") as Person?
        if (person != null) {
            loadPersonDetails(person)
        }
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnDetailCancel.setOnClickListener {
            activity?.finish()
        }
        binding.btnSaveDetail.setOnClickListener {

            val name = binding.txtPersonName.text.toString()
            val lastName = binding.txtLastName.text.toString()
            val age = binding.txtPersonAge.text.toString().toInt()
            val email = binding.txtPersonEmail.text.toString()
            val phone = binding.txtPersonPhone.text.toString()
            val personIntent = Person(name, lastName, age, email, phone)
            personIntent.id = this.personId ?: 0
            val resultIntent = Intent()
            resultIntent.putExtra("person", personIntent)
            activity?.setResult(Activity.RESULT_OK, resultIntent)
            activity?.finish()
        }
    }
}
package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practicanavegacion.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.errorMsg.observe(this) {
            if (it != null) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.user.observe(this) {
            if (it == null) {
                return@observe
            }
            val action =
                SecondFragmentDirections.actionSecondFragmentToClientDashboardFragment(it.name)
            findNavController().navigate(action)
        }
    }

    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnClientSignIn.setOnClickListener {
            val email = binding.txtEmailClient.editText?.text.toString()
            val password = binding.txtPasswordClient.editText?.text.toString()
            viewModel.login(email, password)

        }
        binding.btnCancelClient.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
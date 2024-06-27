package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practicanavegacion.R
import com.example.practicanavegacion.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.apply {
            btnClientAccess.setOnClickListener {
//                val action = MainFragmentDirections.actionMainFragmentToClientDashboardFragment()
                findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
            }
            btnAdminAccess.setOnClickListener {
//                val action = MainFragmentDirections.actionMainFragmentToAdminDashboardFragment()
                findNavController().navigate(R.id.action_mainFragment_to_adminLoginFragment)
            }
            lblAboutApp.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
            }
        }
    }
}
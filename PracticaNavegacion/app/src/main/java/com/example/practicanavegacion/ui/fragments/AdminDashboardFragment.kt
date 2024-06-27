package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.practicanavegacion.databinding.FragmentAdminDashboardBinding

class AdminDashboardFragment : Fragment() {

    companion object {
        fun newInstance() = AdminDashboardFragment()
    }

    private val viewModel: AdminDashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private lateinit var binding: FragmentAdminDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
}
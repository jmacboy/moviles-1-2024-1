package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.practicanavegacion.R
import com.example.practicanavegacion.databinding.FragmentAdminLoginBinding

class AdminLoginFragment : Fragment() {

    companion object {
        fun newInstance() = AdminLoginFragment()
    }

    private val viewModel: AdminLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }
    private lateinit var binding: FragmentAdminLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}
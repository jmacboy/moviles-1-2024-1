package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.practicanavegacion.R
import com.example.practicanavegacion.databinding.FragmentClientDashboardBinding

class ClientDashboardFragment : Fragment() {

    companion object {
        fun newInstance() = ClientDashboardFragment()
    }

    val args: ClientDashboardFragmentArgs by navArgs()
    private val viewModel: ClientDashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private lateinit var binding: FragmentClientDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClientDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lblClientWelcome.text = "Bienvenido ${args.clientName}"
    }
}
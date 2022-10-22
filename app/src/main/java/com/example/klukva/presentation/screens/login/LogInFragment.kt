package com.example.klukva.presentation.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.klukva.R
import com.example.klukva.databinding.FragmentLogInBinding
import com.example.klukva.databinding.FragmentSignInBinding
import com.example.klukva.presentation.screens.signin.SignInViewModel

class LogInFragment : Fragment() {
    lateinit var binding: FragmentLogInBinding
    val viewModelSignInViewModel: LogInViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLogInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textRegistrationAlready.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
package com.example.klukva.presentation.screens.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.klukva.R
import com.example.klukva.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding
    private val viewModelSignInViewModel: SignInViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textRegistrationAlready.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_logInFragment)
        }

        binding.buttonOK.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_dateTimeFragment)
        }

    }
}
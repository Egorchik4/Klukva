package com.example.feature.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.feature.login.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    lateinit var binding: FragmentLogInBinding
    lateinit var viewModelSignInViewModel: LogInViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLogInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textRegistrationNoAccount.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonOK.setOnClickListener {
            //findNavController().navigate(R.id.action_logInFragment_to_dateTimeFragment)
        }
    }

}
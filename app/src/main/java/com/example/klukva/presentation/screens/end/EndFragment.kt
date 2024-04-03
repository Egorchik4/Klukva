package com.example.klukva.presentation.screens.end

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.klukva.R
import com.example.klukva.databinding.FragmentEndBinding

class EndFragment : Fragment() {

	private lateinit var binding: FragmentEndBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentEndBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setListeners()
	}

	private fun setListeners() {
		with(binding) {
			imMenu.setOnClickListener {
				findNavController().navigate(R.id.action_endFragment_to_profileFragment)
			}
			buttonOk.setOnClickListener {
				//findNavController().popBackStack() TODO как навигироваться к первому фрагменту и очитсить стек
			}
		}
	}
}
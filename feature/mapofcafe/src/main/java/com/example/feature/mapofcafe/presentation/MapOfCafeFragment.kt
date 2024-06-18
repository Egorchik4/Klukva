package com.example.feature.mapofcafe.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.component.constants.Constant
import com.example.feature.mapofcafe.databinding.FragmentMapOfCafeBinding

class MapOfCafeFragment : Fragment() {

	lateinit var binding: FragmentMapOfCafeBinding
	private lateinit var viewModelMapOfCafeViewModel: MapOfCafeViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMapOfCafeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		parentFragmentManager.setFragmentResultListener(Constant.PLACEMARK_INFO, viewLifecycleOwner) { _, data ->
			val nameCafe = data.getString(Constant.NAME_OF_CAFE)
			binding.textViewNameOfCafe.text = nameCafe
		}

		setListeners()
	}

	private fun setListeners() {
		with(binding) {
			imMenu.setOnClickListener {
				//findNavController().navigate(R.id.action_mapFragment_to_profileFragment)
			}
			buttonToBook.setOnClickListener {
				//findNavController().navigate(R.id.action_mapOfCafeFragment_to_paymentFragment)
			}
		}
	}

}
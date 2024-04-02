package com.example.klukva.presentation.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.klukva.databinding.FragmentProfileBinding
import com.example.klukva.presentation.screens.profile.bookingadapter.BookingAdapter
import com.example.klukva.presentation.screens.profile.cardadapter.CardAdapter

class ProfileFragment : Fragment() {

	private lateinit var binding: FragmentProfileBinding
	private val viewModel: ProfileViewModel by viewModels()

	private lateinit var cardAdapter: CardAdapter
	private lateinit var bookingAdapter: BookingAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentProfileBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindAdapters()
		setObservers()
		setListeners()
	}

	private fun bindAdapters() {
		bookingAdapter = BookingAdapter()
		binding.rcViewBooking.adapter = bookingAdapter

		cardAdapter = CardAdapter()
		binding.rcViewCard.adapter = cardAdapter
	}

	private fun setObservers() {
		viewModel.cardLive.observe(viewLifecycleOwner) {
			cardAdapter.submitList(it)
		}
		viewModel.bookingLive.observe(viewLifecycleOwner) {
			bookingAdapter.submitList(it)
		}
	}

	private fun setListeners() {
		binding.imCancel.setOnClickListener {
			findNavController().popBackStack()
		}
	}
}
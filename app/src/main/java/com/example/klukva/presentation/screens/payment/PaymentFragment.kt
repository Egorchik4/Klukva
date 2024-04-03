package com.example.klukva.presentation.screens.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.klukva.R
import com.example.klukva.databinding.FragmentPaymentBinding
import com.example.klukva.presentation.screens.payment.orderadapter.OrderAdapter
import com.example.klukva.presentation.screens.profile.cardadapter.CardAdapter

class PaymentFragment : Fragment() {

	private lateinit var binding: FragmentPaymentBinding
	private val viewModel: PaymentViewModel by viewModels()

	private lateinit var orderAdapter: OrderAdapter
	private lateinit var cardAdapter: CardAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentPaymentBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindAdapters()
		setObservers()
		setListeners()
	}

	private fun bindAdapters() {
		orderAdapter = OrderAdapter()
		binding.rcViewCheckPrice.adapter = orderAdapter

		cardAdapter = CardAdapter()
		binding.rcViewCard.adapter = cardAdapter
	}

	private fun setObservers() {
		viewModel.cardLive.observe(viewLifecycleOwner) {
			cardAdapter.submitList(it)
		}
		viewModel.orderLive.observe(viewLifecycleOwner) {
			orderAdapter.submitList(it)
		}
	}

	private fun setListeners() {
		with(binding) {
			imMenu.setOnClickListener {
				findNavController().navigate(R.id.action_paymentFragment_to_profileFragment)
			}
			buttonPay.setOnClickListener {
				//findNavController().navigate(R.id.action_paymentFragment_to_profileFragment)
			}
		}
	}
}
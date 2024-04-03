package com.example.klukva.presentation.screens.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klukva.presentation.screens.payment.orderadapter.OrderEntity
import com.example.klukva.presentation.screens.profile.cardadapter.CardEntity

class PaymentViewModel : ViewModel() {

	private val _orderMut = MutableLiveData<List<OrderEntity>>()
	val orderLive: LiveData<List<OrderEntity>> = _orderMut

	private val _cardMut = MutableLiveData<List<CardEntity>>()
	val cardLive: LiveData<List<CardEntity>> = _cardMut

	init {
		_orderMut.value = listOf(
			OrderEntity("Place1", "330"), OrderEntity("Place2", "100"),
			OrderEntity("Place3", "1000"), OrderEntity("Place4", "1700"),
			OrderEntity("Pla5", "500")
		)
		_cardMut.value = listOf(
			CardEntity("MIR*4364"), CardEntity("VIZA*1234"),
			CardEntity("MIR*7754"), CardEntity("VIZA*1234"),
			CardEntity("MIR*7754")
		)
	}
}
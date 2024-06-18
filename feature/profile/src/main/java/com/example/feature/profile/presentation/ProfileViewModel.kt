package com.example.feature.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature.profile.presentation.bookingadapter.BookingEntity
import com.example.feature.profile.presentation.cardadapter.CardEntity

class ProfileViewModel : ViewModel() {

	private val _cardMut = MutableLiveData<List<CardEntity>>()
	val cardLive: LiveData<List<CardEntity>> = _cardMut

	private val _bookingMut = MutableLiveData<List<BookingEntity>>()
	val bookingLive: LiveData<List<BookingEntity>> = _bookingMut

	init {
		_cardMut.value = listOf(
			CardEntity("MIR*4364"),
			CardEntity("VIZA*1234"),
			CardEntity("MIR*7754"),
			CardEntity("VIZA*1234"),
			CardEntity("MIR*7754")
		)
		_bookingMut.value = listOf(
			BookingEntity(0, "Yoki", "09.10.2023"), BookingEntity(1, "Rest", "25.12.2023"),
			BookingEntity(2, "Bare", "20.07.2023"), BookingEntity(3, "Rest", "25.12.2023"),
			BookingEntity(4, "Yoki", "09.10.2023")
		)
	}
}
package com.example.klukva.presentation.screens.profile.bookingadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BookingAdapter : ListAdapter<BookingEntity, BookingViewHolder>(BookingDiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder =
		BookingViewHolder.from(parent)

	override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
}

class BookingDiffCallback : DiffUtil.ItemCallback<BookingEntity>() {

	override fun areItemsTheSame(oldItem: BookingEntity, newItem: BookingEntity): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: BookingEntity, newItem: BookingEntity): Boolean {
		return oldItem == newItem
	}
}

data class BookingEntity(
	val id: Int,
	val cafe: String,
	val date: String
)
package com.example.klukva.presentation.screens.profile.bookingadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klukva.databinding.BookingItemBinding

class BookingViewHolder(
	private val binding: BookingItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): BookingViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = BookingItemBinding.inflate(inflater, parent, false)
			return BookingViewHolder(binding)
		}
	}

	fun bind(
		bookItem: BookingEntity
	) {
		with(binding) {
			textCafe.text = bookItem.cafe
			textData.text = bookItem.date
		}
	}
}
package com.example.klukva.presentation.screens.payment.orderadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klukva.databinding.OrderItemBinding

class OrderViewHolder(
	private val binding: OrderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): OrderViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = OrderItemBinding.inflate(inflater, parent, false)
			return OrderViewHolder(binding)
		}
	}

	fun bind(
		orderItem: OrderEntity
	) {
		with(binding) {
			textName.text = orderItem.name
			textPrice.text = orderItem.price
		}
	}
}
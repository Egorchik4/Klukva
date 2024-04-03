package com.example.klukva.presentation.screens.payment.orderadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class OrderAdapter : ListAdapter<OrderEntity, OrderViewHolder>(CardDiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
		OrderViewHolder.from(parent)

	override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
}

class CardDiffCallback : DiffUtil.ItemCallback<OrderEntity>() {

	override fun areItemsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
		return oldItem.name == newItem.name
	}

	override fun areContentsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
		return oldItem == newItem
	}
}

data class OrderEntity(
	val name: String,
	val price: String
)
package com.example.feature.payment.presentation.cardadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.feature.payment.domain.entity.CardEntity

class CardAdapter : ListAdapter<CardEntity, CardViewHolder>(CardDiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
		CardViewHolder.from(parent)

	override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
}

class CardDiffCallback : DiffUtil.ItemCallback<CardEntity>() {

	override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
		return oldItem.nameCard == newItem.nameCard
	}

	override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
		return oldItem == newItem
	}
}
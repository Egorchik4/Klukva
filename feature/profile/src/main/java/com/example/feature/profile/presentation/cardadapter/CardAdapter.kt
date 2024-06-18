package com.example.feature.profile.presentation.cardadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

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

data class CardEntity(
	val nameCard: String
)
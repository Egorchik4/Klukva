package com.example.klukva.presentation.screens.profile.cardadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klukva.databinding.CardItemBinding

class CardViewHolder(
	private val binding: CardItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): CardViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = CardItemBinding.inflate(inflater, parent, false)
			return CardViewHolder(binding)
		}
	}

	fun bind(
		cardItem: CardEntity
	) {
		with(binding) {
			textCard.text = cardItem.nameCard
		}
	}
}
package com.example.feature.map.presentation.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature.map.R
import com.example.feature.map.data.model.LocationModel
import com.example.feature.map.databinding.ViewPagerItem1Binding
import com.example.feature.map.databinding.ViewPagerItem2Binding
import com.example.feature.map.databinding.ViewPagerItem3Binding

class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.CustomAdapter>() {

	private var model: LocationModel? = null

	sealed class CustomAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

		class ViewHolderItemFirst(itemView: View) : CustomAdapter(itemView) {
			private val binding = ViewPagerItem1Binding.bind(itemView)
			fun bind(model: LocationModel?){
				if(model != null){
					binding.textViewName.text = model.name
					binding.textViewKitchenName.text = model.kitchen_name
					binding.textViewAdres.text = model.adres
					binding.textViewOpenTime.text = model.open_time
					binding.textViewLoad.text = model.load
					Glide
						.with(binding.imageView.context)
						.load(model.picture_location)
						.into(binding.imageView)

				}
			}
		}

		class ViewHolderItemSecond(itemView: View) : CustomAdapter(itemView) {
			private val binding = ViewPagerItem2Binding.bind(itemView)
			fun bind(model: LocationModel?){}
		}

		class ViewHolderItemThird(itemView: View) : CustomAdapter(itemView) {
			private val binding = ViewPagerItem3Binding.bind(itemView)
			fun bind(model: LocationModel?){
				if(model != null){
					binding.textViewAverageRating.text = model.average_rating
					binding.textViewNumberOfReviews.text = model.number_of_reviews
				}
			}
		}
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter {
		return when (viewType) {
			0    -> {
				val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item_1,parent,false)
				CustomAdapter.ViewHolderItemFirst(view)
			}
			1    -> {
				val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item_2, parent, false)
				CustomAdapter.ViewHolderItemSecond(view)
			}
			else -> {
				val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item_3, parent, false)
				CustomAdapter.ViewHolderItemThird(view)
			}
		}
	}

	override fun onBindViewHolder(holder: CustomAdapter, position: Int) {
		when (getItemViewType(position)){
			0 -> {
				(holder as CustomAdapter.ViewHolderItemFirst).bind(model)
			}
			1 -> {
				(holder as CustomAdapter.ViewHolderItemSecond).bind(model)
			}
			else                           -> {
				(holder as CustomAdapter.ViewHolderItemThird).bind(model)
			}
		}
	}

	override fun getItemCount(): Int {
		return 3
	}

	override fun getItemViewType(position: Int): Int {
		return when (position) {
			0    -> {
				0
			}
			1    -> {
				1
			}
			else -> {
				2
			}
		}
	}


	fun updateData(modelOfPlace: LocationModel){
		model = modelOfPlace
		notifyDataSetChanged()
	}

}
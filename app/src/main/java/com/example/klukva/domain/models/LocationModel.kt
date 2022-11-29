package com.example.klukva.domain.models

import com.yandex.mapkit.geometry.Point

data class LocationModel(
	var id: Int,
	var location: Point,
	var name: String,
	var open_time: String,
	var load: String,
	var kitchen_name: String,
	var adres: String,
	var average_rating: String,
	var number_of_reviews: String,
	var picture_location: Int
)

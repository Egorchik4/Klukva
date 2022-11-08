package com.example.klukva.domain.models

import com.yandex.mapkit.geometry.Point

data class LocationModel(
	var point: Point,
	var name: String,
	var time: String,
	var load: Char
)

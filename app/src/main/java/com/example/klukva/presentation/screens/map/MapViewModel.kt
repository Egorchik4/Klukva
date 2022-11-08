package com.example.klukva.presentation.screens.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klukva.domain.models.LocationModel
import com.yandex.mapkit.geometry.Point

class MapViewModel : ViewModel() {

    private val dataMapPointMut = MutableLiveData<MutableList<LocationModel>>()
    val dataMapPointLive: LiveData<MutableList<LocationModel>> = dataMapPointMut

    private var listPoint: MutableList<LocationModel> = mutableListOf(
        LocationModel(
            point = Point(56.464326, 84.956659),
            name = "Молчание ягнят",
            time = "c 9 до 21",
            load = '3'
        ),
        LocationModel(
            point = Point(56.463883, 84.957018),
            name = "Yoki Toki",
            time = "c 9 до 23",
            load = '5'
        ),
        LocationModel(
            point = Point(56.463769, 84.951853),
            name = "Pizza",
            time = "c 12 до 20",
            load = '1'
        )
    )
    init{
        dataMapPointMut.value = listPoint
    }
}
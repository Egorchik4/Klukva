package com.example.klukva.presentation.screens.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klukva.R
import com.example.klukva.domain.models.LocationModel
import com.yandex.mapkit.geometry.Point

class MapViewModel : ViewModel() {

    private val dataMapPointMut = MutableLiveData<MutableList<LocationModel>>()
    val dataMapPointLive: LiveData<MutableList<LocationModel>> = dataMapPointMut

    private var listPoint: MutableList<LocationModel> = mutableListOf(
        LocationModel(
            id = 0,
            location = Point(56.470964, 84.965902),
            name = "Молчание ягнят",
            open_time = "c 9 до 21",
            load = "3",
            kitchen_name = "Восточная кухня",
            adres = "п.Комсомольский,16а",
            average_rating = "4",
            number_of_reviews = "100",
            picture_location = R.drawable.molchanie_yagnet_test
        ),
        LocationModel(
            id = 1,
            location = Point(56.464326, 84.956659),
            name = "Yoki Toki",
            open_time = "c 9 до 23",
            load = "5",
            kitchen_name = "Азиатская кухня",
            adres = "ул.Усова,6,стр.12",
            average_rating = "5",
            number_of_reviews = "1000",
            picture_location = R.drawable.yoki_toki_test
        ),
        LocationModel(
            id = 2,
            location = Point(56.478094, 84.950290),
            name = "Make Love Pizza",
            open_time = "c 12 до 20",
            load = "1",
            kitchen_name = "Фаст-Фуд",
            adres = "просп.Ленина,85А,",
            average_rating = "5",
            number_of_reviews = "4000",
            picture_location = R.drawable.make_love_pizza_test
        )
    )
    init{
        dataMapPointMut.value = listPoint
    }

    fun getModelFromId(id: Int): LocationModel{
        return listPoint[id]
    }

}
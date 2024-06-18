package com.example.feature.datetime.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DateTimeViewModel : ViewModel() {
    private val dataMut = MutableLiveData<List<String>>()
    val dataLive: LiveData<List<String>> = dataMut

    private val durationMut = MutableLiveData<List<String>>()
    val durationLive: LiveData<List<String>> = durationMut

    private val timeMut = MutableLiveData<List<String>>()
    val timeLive: LiveData<List<String>> = timeMut

    private val itemsData: List<String> = listOf("Сегодня","Завтра","Послезавтра")
    private val itemsDuration = listOf("1 час","2 часа","3 часа","4 часа","5 часов","6 часов")
    private val itemsTime = listOf("8:00","8:30","9:00","9:30","10:00","10:30","11:00",
        "11:30","12:00","12:30","13:00","13:30","14:00","14:30")

init {
    dataMut.value = itemsData
    durationMut.value = itemsDuration
    timeMut.value = itemsTime
}


}
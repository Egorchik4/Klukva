package com.example.klukva.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.klukva.R
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Launcher)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(mainViewModel.apiKey == null){
            Log.e("eee", "INITIALIZE")
            mainViewModel.apiKey = "c1ad8195-3f34-4648-ad39-bdd809bc8e6e"
            MapKitFactory.setApiKey(mainViewModel.apiKey!!)
            MapKitFactory.initialize(this)
        }
    }
}
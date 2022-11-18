package com.example.klukva.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.klukva.R
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Launcher)
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("c1ad8195-3f34-4648-ad39-bdd809bc8e6e")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
    }
}
package com.example.klukva.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
		if (mainViewModel.apiKey == null) {
			mainViewModel.apiKey = "c1ad8195-3f34-4648-ad39-bdd809bc8e6e"
			MapKitFactory.setApiKey(mainViewModel.apiKey!!)
			MapKitFactory.initialize(this)
		}
	}

}
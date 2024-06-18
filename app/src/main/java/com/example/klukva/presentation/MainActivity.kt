package com.example.klukva.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.klukva.R
import com.example.component.R as Res

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		setTheme(Res.style.Launcher)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
//		if (mainViewModel.apiKey == null) {
//			mainViewModel.apiKey = "c1ad8195-3f34-4648-ad39-bdd809bc8e6e"
//			MapKitFactory.setApiKey(mainViewModel.apiKey!!)
//			MapKitFactory.initialize(this)
//		}
	}
}
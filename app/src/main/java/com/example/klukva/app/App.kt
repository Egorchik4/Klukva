package com.example.klukva.app

import android.app.Application
import com.example.feature.signin.di.DaggerSignInComponent
import com.example.feature.signin.di.SignInComponent
import com.example.feature.signin.di.SignInComponentProvider
import com.example.klukva.di.AppComponent
import com.example.klukva.di.DaggerAppComponent

class App : Application(), SignInComponentProvider {

	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.create()
	}

	override fun provideSignInComponent(): SignInComponent {
		return DaggerSignInComponent.builder().depends(appComponent).build()
	}
}
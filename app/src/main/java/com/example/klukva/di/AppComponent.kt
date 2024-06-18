package com.example.klukva.di

import com.example.component.MultiViewModelFactory
import com.example.feature.signin.di.SignInDepends
import com.example.feature.signin.di.SignInModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, SignInModule::class])
interface AppComponent : SignInDepends {

	override val factory: MultiViewModelFactory
	override val retrofit: Retrofit

	@Component.Builder
	interface Builder {

		fun build(): AppComponent
	}
}
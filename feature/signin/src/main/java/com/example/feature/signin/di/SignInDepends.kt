package com.example.feature.signin.di

import com.example.component.MultiViewModelFactory
import retrofit2.Retrofit

interface SignInDepends {

	val retrofit: Retrofit
	val factory: MultiViewModelFactory
}
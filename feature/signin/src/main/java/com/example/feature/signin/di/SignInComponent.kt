package com.example.feature.signin.di

import com.example.feature.signin.presentation.SignInFragment
import dagger.Component

@Component(modules = [SignInModule::class], dependencies = [SignInDepends::class])
interface SignInComponent {

	fun inject(signInFragment: SignInFragment)

	@Component.Builder
	interface Builder {

		fun depends(signInDepends: SignInDepends): Builder

		fun build(): SignInComponent
	}
}
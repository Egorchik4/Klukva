package com.example.klukva.di

import androidx.lifecycle.ViewModel
import com.example.feature.signin.domain.usecase.SignInUseCase
import com.example.feature.signin.presentation.SignInViewModel
import com.example.klukva.di.annotation.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

	@Provides
	@[IntoMap ViewModelKey(SignInViewModel::class)]
	fun provideSignInViewModel(
		signInUseCase: SignInUseCase,
	): ViewModel {
		return SignInViewModel(
			signInUseCase = signInUseCase,
		)
	}
}
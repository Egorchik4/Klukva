package com.example.feature.signin.di

import com.example.feature.signin.data.api.SignInApi
import com.example.feature.signin.data.datasource.SignInDataSource
import com.example.feature.signin.data.datasource.SignInDataSourceImpl
import com.example.feature.signin.data.repository.SignInRepositoryImpl
import com.example.feature.signin.domain.repository.SignInRepository
import com.example.feature.signin.domain.usecase.SignInUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SignInModule {

	@Provides
	@Singleton
	fun provideSignApi(retrofit: Retrofit): SignInApi {
		return retrofit.create(SignInApi::class.java)
	}

	@Provides
	@Singleton
	fun provideSignInDataSource(api: SignInApi): SignInDataSource {
		return SignInDataSourceImpl(api)
	}

	@Provides
	@Singleton
	fun provideSignInRepository(dataSource: SignInDataSource): SignInRepository {
		return SignInRepositoryImpl(dataSource)
	}

	@Provides
	@Singleton
	fun provideSignInUseCase(repository: SignInRepository): SignInUseCase {
		return SignInUseCase(repository)
	}
}
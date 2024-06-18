package com.example.feature.signin.domain.usecase

import com.example.feature.signin.domain.entity.SignInEntity
import com.example.feature.signin.domain.repository.SignInRepository

class SignInUseCase(
	private val repository: SignInRepository
) : suspend (SignInEntity) -> Boolean by repository::signIn
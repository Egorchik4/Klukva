package com.example.feature.signin.data.repository

import com.example.feature.signin.data.datasource.SignInDataSource
import com.example.feature.signin.data.mapper.toModel
import com.example.feature.signin.domain.entity.SignInEntity
import com.example.feature.signin.domain.repository.SignInRepository

class SignInRepositoryImpl(private val signInDataSource: SignInDataSource) : SignInRepository {

	override suspend fun signIn(signInEntity: SignInEntity): Boolean =
		signInDataSource.signIn(signInEntity.toModel())
}
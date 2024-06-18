package com.example.feature.signin.domain.repository

import com.example.feature.signin.domain.entity.SignInEntity

interface SignInRepository {

	suspend fun signIn(signInEntity: SignInEntity): Boolean
}
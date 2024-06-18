package com.example.feature.signin.data.datasource

import com.example.feature.signin.data.api.SignInApi
import com.example.feature.signin.data.model.SignInModel

class SignInDataSourceImpl(private val signInApi: SignInApi) : SignInDataSource {

	override suspend fun signIn(signInModel: SignInModel): Boolean =
		signInApi.signIn(signInModel)

}

interface SignInDataSource {

	suspend fun signIn(signInModel: SignInModel): Boolean
}
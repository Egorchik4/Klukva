package com.example.feature.signin.data.api

import com.example.feature.signin.data.model.SignInModel
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {

	@POST("register")
	suspend fun signIn(@Body signInModel: SignInModel): Boolean
}
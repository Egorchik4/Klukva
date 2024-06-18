package com.example.feature.signin.domain.entity

data class SignInEntity(
	val phoneNumber: String,
	val password: String,
	val firstName: String,
	val userType: String,
)
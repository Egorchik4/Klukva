package com.example.feature.signin.data.mapper

import com.example.feature.signin.data.model.SignInModel
import com.example.feature.signin.domain.entity.SignInEntity

fun SignInEntity.toModel(): SignInModel =
	SignInModel(
		phoneNumber = phoneNumber,
		password = password,
		firstName = firstName,
		userType = userType,
	)
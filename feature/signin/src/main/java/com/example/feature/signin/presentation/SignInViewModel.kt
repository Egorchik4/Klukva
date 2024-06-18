package com.example.feature.signin.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.signin.domain.entity.SignInEntity
import com.example.feature.signin.domain.usecase.SignInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
	private val signInUseCase: SignInUseCase,
) : ViewModel() {

	private val _mutLiveData = MutableLiveData<Boolean>()
	val liveData: LiveData<Boolean> = _mutLiveData

	fun signIn(phoneNumber: String, password: String, name: String) {
		if (name.isNotEmpty() && phoneNumber.isNotEmpty() && password.isNotEmpty()) {
			viewModelScope.launch {
				try {
					signInUseCase(
						SignInEntity(
							phoneNumber = phoneNumber,
							password = password,
							firstName = name,
							userType = "",
						)
					)
					_mutLiveData.value = true
				} catch (e: Exception) {
					Log.e("eee", "Error: $e")
					_mutLiveData.value = false
				}
			}
		} else {
			_mutLiveData.value = false
		}
	}
}

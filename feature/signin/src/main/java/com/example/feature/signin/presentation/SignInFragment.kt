package com.example.feature.signin.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.component.MultiViewModelFactory
import com.example.feature.signin.databinding.FragmentSignInBinding
import com.example.feature.signin.di.SignInComponent
import com.example.feature.signin.di.SignInComponentProvider
import javax.inject.Inject

class SignInFragment : Fragment() {

	lateinit var binding: FragmentSignInBinding

	@Inject
	lateinit var viewModelFactory: MultiViewModelFactory
	private val viewModel by viewModels<SignInViewModel> { viewModelFactory }

	override fun onAttach(context: Context) {
		getSignInComponent(context)
			.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentSignInBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setObservers()
		setListeners()
	}

	private fun setObservers() {
		viewModel.liveData.observe(viewLifecycleOwner) {
			if (it) {
				findNavController().navigate(Uri.parse("myApp://dateTimeFragment"))
			}
		}
	}

	private fun setListeners() {
		with(binding) {
			textRegistrationAlready.setOnClickListener {
				findNavController().navigate(Uri.parse("myApp://logInFragment"))
			}

			buttonOK.setOnClickListener {
				viewModel.signIn(
					phoneNumber = editTextLogin.text.toString(),
					password = editTextPassword.text.toString(),
					name = editTextName.text.toString(),
				)
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		// переворот ??
		if (!requireActivity().isChangingConfigurations) {
			signInComponent = null
		}
	}

	companion object {

		private var signInComponent: SignInComponent? = null

		fun getSignInComponent(context: Context): SignInComponent {
			if (signInComponent == null) {
				signInComponent = (context.applicationContext as SignInComponentProvider)
					.provideSignInComponent()
			}
			return requireNotNull(signInComponent)
		}
	}
}
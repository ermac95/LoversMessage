package com.mycodeflow.loversmessage.presentation.ui.authorization

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycodeflow.loversmessage.MyApp
import com.mycodeflow.loversmessage.R
import com.mycodeflow.loversmessage.databinding.FragmentRegisterScreenBinding
import com.mycodeflow.loversmessage.presentation.viewmodels.AppViewModelFactory
import com.mycodeflow.loversmessage.presentation.viewmodels.AuthViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class RegisterScreen : Fragment(R.layout.fragment_register_screen) {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var authViewModel: AuthViewModel

    private val vb by viewBinding(FragmentRegisterScreenBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupListeners()
        setupTransition(view)
    }

    private fun setupTransition(view: View) {
        lifecycleScope.launchWhenResumed {
            authViewModel.loggedIn.collectLatest { userLoggedIn->
                if(userLoggedIn){
                    Navigation.findNavController(view).popBackStack()
                }
            }
        }
    }

    private fun setupViewModel() {
        authViewModel = ViewModelProvider(this, viewModelFactory)
                .get(AuthViewModel::class.java)
    }

    private fun setupListeners() {
        vb.registerButton.setOnClickListener {
            when {
                TextUtils.isEmpty(vb.regFieldEmail.text.toString()) -> {
                    vb.regFieldEmail.error = "Please enter your email"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(vb.regFieldPass.text.toString()) -> {
                    vb.regFieldPass.error = "Please enter your password"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(vb.regFieldName.text.toString()) -> {
                    vb.regFieldName.error = "Please enter your name"
                    return@setOnClickListener
                }
                else -> {
                    val userEmail = vb.regFieldEmail.text.toString()
                    val userPass = vb.regFieldPass.text.toString()
                    val userName = vb.regFieldName.text.toString()
                    authViewModel.createRegisterRequest(userEmail, userPass, userName)
                }
            }
        }
    }
}
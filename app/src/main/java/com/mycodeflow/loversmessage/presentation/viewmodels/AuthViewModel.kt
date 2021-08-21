package com.mycodeflow.loversmessage.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycodeflow.loversmessage.domain.model.RegisterRequest
import com.mycodeflow.loversmessage.domain.model.SimpleResponse
import com.mycodeflow.loversmessage.domain.repositories.AuthRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class AuthViewModel @Inject constructor(
        val authRepository: AuthRepository
): ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.java.simpleName, "CoroutineExceptionHandler:$throwable")
    }

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _loggedIn = MutableStateFlow(false)
    val loggedIn: StateFlow<Boolean> = _loggedIn

    private val _userToken = MutableStateFlow("")
    val userToken: StateFlow<String> = _userToken

    fun createRegisterRequest(userEmail: String, userPass: String, userName: String){
        val registerRequest = RegisterRequest(
                email = userEmail,
                name = userName,
                password = userPass
        )
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            var userData: SimpleResponse? = null
            try {
                Log.d("myLogs", "userData = $userData")
                userData = authRepository.registerUser(registerRequest)
                _userToken.value = userData.message
                _loggedIn.value = true
                Log.d("myLogs", "User token = ${userData.message}")
            } catch (e: Exception){
                Log.d("myLogs", "Exception occurred during UserRegister api call is ${e.message}")
            }
        }
    }
}
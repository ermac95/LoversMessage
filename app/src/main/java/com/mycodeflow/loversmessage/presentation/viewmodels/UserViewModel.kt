package com.mycodeflow.loversmessage.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycodeflow.loversmessage.domain.model.User
import com.mycodeflow.loversmessage.domain.repositories.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserViewModel @Inject constructor(
        val userRepository: UserRepository
): ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
        Log.e(this::class.java.simpleName, "CoroutineExceptionHandler:$throwable")
    }

    private val _userData = MutableStateFlow<User?>(null)
    val userData: StateFlow<User?> = _userData

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun findUserByName(userName: String){
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _loading.value = true
            val response = userRepository.findUserByName(userName)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val userResponse = response.body()
                    if (userResponse!= null){
                        val user = User(
                                userName = userResponse.userName,
                                email = userResponse.email,
                                hashPassword = userResponse.hashPassword
                        )
                        _userData.value = user
                    }
                    _loading.value = false
                    _errorMessage.value = ""
                } else {
                    onError("User with such name not found")
                    _userData.value = null
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        _loading.value = false
    }

    fun sendInvitation(userName: String?) {

    }
}
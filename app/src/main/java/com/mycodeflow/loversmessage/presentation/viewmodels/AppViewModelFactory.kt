package com.mycodeflow.loversmessage.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.loversmessage.domain.repositories.AuthRepository
import com.mycodeflow.loversmessage.domain.repositories.MessageRepository
import com.mycodeflow.loversmessage.domain.repositories.UserRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory @Inject constructor(
    private val messageRepository: MessageRepository,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){
        MessageViewModel::class.java -> MessageViewModel(messageRepository)
        AuthViewModel::class.java -> AuthViewModel(authRepository)
        UserViewModel::class.java -> UserViewModel(userRepository)
        else -> throw IllegalArgumentException("wrong dependencies")
    } as T
}
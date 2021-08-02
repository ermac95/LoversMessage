package com.mycodeflow.loversmessage.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.mycodeflow.loversmessage.domain.model.LoveCardItem
import com.mycodeflow.loversmessage.domain.repositories.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MessageViewModel(
        val messageRepo: MessageRepository
): ViewModel() {

    private val _messages = MutableStateFlow<List<LoveCardItem>>(emptyList())
    val messages: StateFlow<List<LoveCardItem>> get() = _messages

    fun createNewMessage(message: LoveCardItem){
        //repo.createNewMessage(message)
    }

    fun getCurrentMessages(){
        //_messages.value = repo.getAllMessages()
    }
}
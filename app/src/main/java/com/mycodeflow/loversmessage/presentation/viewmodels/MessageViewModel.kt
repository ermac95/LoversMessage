package com.mycodeflow.loversmessage.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycodeflow.loversmessage.domain.model.LoveCard
import com.mycodeflow.loversmessage.domain.model.LoveCardTemplate
import com.mycodeflow.loversmessage.domain.repositories.MessageRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MessageViewModel @Inject constructor(
        val messageRepository: MessageRepository
): ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.java.simpleName, "CoroutineExceptionHandler:$throwable")
    }

    private val _messages = MutableStateFlow<List<LoveCardTemplate>>(emptyList())
    val messages: StateFlow<List<LoveCardTemplate>> get() = _messages

    fun setImageAndMessage(imageIndex: Int, message: String){
        messageRepository.setImageIndexAndMessage(imageIndex, message)
    }

    fun setDateAndTime(date: String, time: String){
        messageRepository.setDateAndTime(date, time)
    }

    fun getCurrentMessages(){
        var loveCards: List<LoveCard> = emptyList()
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler ) {
            try {
                loveCards = messageRepository.getAllLoveCards()
            } catch (e: Exception){
                Log.d("myLogs", "Exception occurred during GetAllLoveCards api call is ${e.message}")
            }
        }
    }
}
package com.mycodeflow.loversmessage.domain.repositories

import android.util.Log
import com.mycodeflow.loversmessage.data.api.LoveMessageApi
import com.mycodeflow.loversmessage.domain.model.LoveCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MessageRepositorySource{

    fun setImageIndexAndMessage(index: Int, message: String)

    fun setDateAndTime(date: String, time: String)

    fun createLoveCard()

    suspend fun getAllLoveCards(): List<LoveCard>
}

class MessageRepository @Inject constructor(
    val networkApi: LoveMessageApi
): MessageRepositorySource {

    private val _imageIndex = MutableStateFlow(0)
    val imageIndex: StateFlow<Int> = _imageIndex

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> = _date

    private val _time = MutableStateFlow("")
    val time: StateFlow<String> = _time

    private val loveCards = ArrayList<LoveCard>()

    val loveCardsList: Flow<MutableList<LoveCard>> = flow {
        emit(loveCards)
    }

    override fun setImageIndexAndMessage(index: Int, message: String) {
        _imageIndex.value = index
        _message.value = message
    }

    override fun setDateAndTime(date: String, time: String) {
        _date.value = date
        _time.value = time
    }

    override fun createLoveCard() {
        /*
        val loveCard = LoveCard(
                imageIndex = imageIndex.value,
                text = message.value,
                sendDate = date.value,
                sendTime = time.value
        )
         */
        //loveCards.add(loveCard)
        //sendLoveCardToServer(loveCard)
    }

    override suspend fun getAllLoveCards(): List<LoveCard> {
        Log.d("myLogs", "GetLoveCards fun called in coroutine scope")
        val loveCards: List<LoveCard> = networkApi.getAllLoveCards()
        Log.d("myLogs", "LoveCards = $loveCards")
        return loveCards
    }

    suspend fun sendLoveCardToServer(loveCard: LoveCard){
        //send to server
    }

}
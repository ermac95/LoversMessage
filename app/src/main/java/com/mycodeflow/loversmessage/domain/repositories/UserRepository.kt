package com.mycodeflow.loversmessage.domain.repositories

import com.mycodeflow.loversmessage.data.api.LoveMessageApi
import com.mycodeflow.loversmessage.data.entities.UserResponse
import retrofit2.Response
import javax.inject.Inject

interface UserRepositorySource{

    suspend fun findUserByName(userName: String): Response<UserResponse>

    suspend fun sendInvitation(userName: String?)

}

class UserRepository @Inject constructor(
        val netWorkApi: LoveMessageApi
): UserRepositorySource {

    override suspend fun findUserByName(userName: String): Response<UserResponse> {
        return netWorkApi.findUserByName(userName)
    }

    override suspend fun sendInvitation(userName: String?) {
        //get current user's name via sharedPrefs
        //send request to server with both names netWorkApi.sendInvitation(senderName, receiverName)
    }
}
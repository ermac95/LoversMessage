package com.mycodeflow.loversmessage.domain.repositories

import com.mycodeflow.loversmessage.data.api.LoveMessageApi
import com.mycodeflow.loversmessage.data.dto.UserResponse
import retrofit2.Response
import javax.inject.Inject

interface UserRepositorySource{

    suspend fun findUserByName(userName: String): Response<UserResponse>

}

class UserRepository @Inject constructor(
        val netWorkApi: LoveMessageApi
): UserRepositorySource {

    override suspend fun findUserByName(userName: String): Response<UserResponse> {
        return netWorkApi.findUserByName(userName)
    }
}
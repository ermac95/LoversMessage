package com.mycodeflow.loversmessage.domain.repositories

import android.util.Log
import com.mycodeflow.loversmessage.data.api.LoveMessageApi
import com.mycodeflow.loversmessage.data.database.UserDao
import com.mycodeflow.loversmessage.data.database.UserDataBase
import com.mycodeflow.loversmessage.domain.model.LoginRequest
import com.mycodeflow.loversmessage.domain.model.RegisterRequest
import com.mycodeflow.loversmessage.domain.model.SimpleResponse
import com.mycodeflow.loversmessage.domain.model.User
import javax.inject.Inject


interface AuthRepositorySource{

    suspend fun registerUser(registerRequest: RegisterRequest): SimpleResponse

    suspend fun loginUser(loginRequest: LoginRequest)
}

class AuthRepository @Inject constructor(
        val networkApi: LoveMessageApi,
        val localDataBase: UserDao
): AuthRepositorySource {

    override suspend fun registerUser(registerRequest: RegisterRequest): SimpleResponse{
        val userResponse = networkApi.registerUser(registerRequest)
        //saving token to SharedPreferences
        saveUserToDatabase(registerRequest)
        return userResponse
    }

    private suspend fun saveUserToDatabase(registerRequest: RegisterRequest) {
        val userProfile = User(
                email = registerRequest.email,
                hashPassword = registerRequest.password,
                userName = registerRequest.name,
                loversList = mutableListOf(),
                loveCards = mutableListOf()
        )
        localDataBase.insertUser(userProfile)
    }

    override suspend fun loginUser(loginRequest: LoginRequest) {
        TODO("Not yet implemented")
    }

}
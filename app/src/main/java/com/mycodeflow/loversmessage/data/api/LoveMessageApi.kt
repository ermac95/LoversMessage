package com.mycodeflow.loversmessage.data.api

import com.mycodeflow.loversmessage.domain.model.LoginRequest
import com.mycodeflow.loversmessage.domain.model.LoveCard
import com.mycodeflow.loversmessage.domain.model.RegisterRequest
import com.mycodeflow.loversmessage.domain.model.SimpleResponse
import retrofit2.http.*

interface LoveMessageApi {

    //Functionality of the LoveCardsAPI
    @Headers("Content-type: application/json")
    @POST("/v1/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): SimpleResponse

    @Headers("Content-type: application/json")
    @POST("/v1/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): SimpleResponse

    @GET("/v1/lovecards")
    suspend fun getAllLoveCards(): List<LoveCard>

}
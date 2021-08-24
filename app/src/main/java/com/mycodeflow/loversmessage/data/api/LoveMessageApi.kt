package com.mycodeflow.loversmessage.data.api

import com.mycodeflow.loversmessage.data.dto.UserResponse
import com.mycodeflow.loversmessage.domain.model.LoginRequest
import com.mycodeflow.loversmessage.domain.model.LoveCard
import com.mycodeflow.loversmessage.domain.model.RegisterRequest
import com.mycodeflow.loversmessage.domain.model.SimpleResponse
import retrofit2.Response
import retrofit2.http.*

interface LoveMessageApi {

    //Functionality of the LoveCardsAPI
    @Headers("Content-type: application/json")
    @POST("/v1/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): SimpleResponse

    @Headers("Content-type: application/json")
    @POST("/v1/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): SimpleResponse

    @Headers("Content-type: application/json")
    @GET("/v1/users/{user_name}")
    suspend fun findUserByName(
            @Path("user_name") userName: String
    ): Response<UserResponse>

    @GET("/v1/lovecards")
    suspend fun getAllLoveCards(): List<LoveCard>

}
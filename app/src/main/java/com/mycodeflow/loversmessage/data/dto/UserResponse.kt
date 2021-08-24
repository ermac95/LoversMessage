package com.mycodeflow.loversmessage.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
        val email: String,
        val hashPassword: String,
        val userName: String,
)

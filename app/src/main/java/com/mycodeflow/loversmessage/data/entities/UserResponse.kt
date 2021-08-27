package com.mycodeflow.loversmessage.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
        val email: String,
        val hashPassword: String,
        val userName: String,
)

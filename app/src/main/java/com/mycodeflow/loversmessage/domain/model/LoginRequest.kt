package com.mycodeflow.loversmessage.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val name: String,
    val password: String
)

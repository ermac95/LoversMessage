package com.mycodeflow.loversmessage.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse(
    val success: Boolean,
    val message: String
)

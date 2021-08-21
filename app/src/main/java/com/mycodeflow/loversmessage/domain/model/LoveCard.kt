package com.mycodeflow.loversmessage.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoveCard(
        val imageIndex: Int,
        val text: String,
        val sendDate: String,
        val sendTime: String,
        val receiver: String
)
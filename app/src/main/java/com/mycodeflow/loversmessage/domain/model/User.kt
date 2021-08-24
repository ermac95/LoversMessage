package com.mycodeflow.loversmessage.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class User(
    @PrimaryKey
    val email: String,
    val hashPassword: String,
    val userName: String,
    val loversList: MutableList<User>? = mutableListOf(),
    val loveCards: MutableList<LoveCard>? = mutableListOf()
)

package com.mycodeflow.loversmessage.data.database

import androidx.room.TypeConverter
import com.mycodeflow.loversmessage.domain.model.LoveCard
import com.mycodeflow.loversmessage.domain.model.User
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


internal class UserTypeConverter {

    @TypeConverter
    fun loversToString(lovers: MutableList<User>): String{
        return Json.encodeToString(ListSerializer(User.serializer()), lovers)
    }

    @TypeConverter
    fun loversFromString(loversString: String): MutableList<User>{
        return Json.decodeFromString(loversString)
    }

    @TypeConverter
    fun loveCardsToString(loveCards: MutableList<LoveCard>): String{
        return Json.encodeToString(ListSerializer(LoveCard.serializer()), loveCards)
    }

    @TypeConverter
    fun loveCardsFromString(loveCardsString: String): MutableList<LoveCard>{
        return Json.decodeFromString(loveCardsString)
    }
}
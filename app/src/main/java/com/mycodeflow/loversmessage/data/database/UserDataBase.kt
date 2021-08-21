package com.mycodeflow.loversmessage.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mycodeflow.loversmessage.domain.model.User

@Database(entities = [User::class], version = 1)
@TypeConverters(UserTypeConverter::class)
abstract class UserDataBase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {

        fun getInstance(context: Context): UserDataBase{
            return Room.databaseBuilder(
                    context,
                    UserDataBase::class.java,
                    "user_database"
            ).build()
        }
    }
}
package com.mycodeflow.loversmessage.di

import android.content.Context
import com.mycodeflow.loversmessage.data.database.UserDao
import com.mycodeflow.loversmessage.data.database.UserDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideUserDataBase(context: Context): UserDataBase{
        return UserDataBase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(userDataBase: UserDataBase): UserDao{
        return userDataBase.getUserDao()
    }
}
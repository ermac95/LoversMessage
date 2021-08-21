package com.mycodeflow.loversmessage.di

import com.mycodeflow.loversmessage.domain.repositories.AuthRepository
import com.mycodeflow.loversmessage.domain.repositories.MessageRepository
import com.mycodeflow.loversmessage.presentation.viewmodels.AppViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoveCardModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        messageRepository: MessageRepository,
        authRepository: AuthRepository
    ): AppViewModelFactory{
        return AppViewModelFactory(messageRepository, authRepository)
    }
}
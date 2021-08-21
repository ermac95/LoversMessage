package com.mycodeflow.loversmessage.di

import android.content.Context
import com.mycodeflow.loversmessage.presentation.ui.MessageScreen
import com.mycodeflow.loversmessage.presentation.ui.NewMessageFirstPage
import com.mycodeflow.loversmessage.presentation.ui.NewMessageSecondPage
import com.mycodeflow.loversmessage.presentation.ui.authorization.RegisterScreen
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LoveCardModule::class, NetworkModule::class, DataBaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(frag: NewMessageFirstPage)

    fun inject(frag: NewMessageSecondPage)

    fun inject(frag: RegisterScreen)

    fun inject(frag: MessageScreen)
}
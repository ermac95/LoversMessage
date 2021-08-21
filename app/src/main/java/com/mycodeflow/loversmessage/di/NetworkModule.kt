package com.mycodeflow.loversmessage.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mycodeflow.loversmessage.data.api.LoveMessageApi
import com.mycodeflow.loversmessage.data.api.TokenInterceptor
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpBuilder = OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .addInterceptor(tokenInterceptor)
        return okHttpBuilder.build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideLoveCardsApiService(okHttpClient: OkHttpClient): LoveMessageApi {
        return Retrofit.Builder().addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .client(okHttpClient)
                .baseUrl("https://lovecards-server.herokuapp.com")
                .build()
                .create(LoveMessageApi::class.java)
    }
}
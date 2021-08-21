package com.mycodeflow.loversmessage.data.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //TODO Getting token from sharedPrefs of current User
        val authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMb3ZlQ2FyZEF1dGgiLCJpc3MiOiJsb3ZlY2FyZHNTZXJ2ZXIiLCJlbWFpbCI6ImVybWFjMkBtYWlsLnJ1In0.tKkJ0bQkQuqeSQN1Vip6XizcV8RUaS3mQ5FkspeJOccGTgCm37IcbmRfz441Kmofi1ToRpYW4SNCod4AEBUfhA"
        val original = chain.request()

        if (original.url.encodedPath.contains("/register") && original.method == "post"
                || (original.url.encodedPath.contains("/login") && original.method == "post")
        ) {
            return  chain.proceed(original)
        }

        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .url(originalHttpUrl)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
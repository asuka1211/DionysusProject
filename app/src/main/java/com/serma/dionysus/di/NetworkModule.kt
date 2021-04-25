package com.serma.dionysus.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.serma.dionysus.auth.AuthInterceptor
import com.serma.dionysus.auth.TokenAuthenticator
import com.serma.dionysus.auth.manager.SessionManager
import com.serma.dionysus.auth.manager.SessionManagerImpl
import com.serma.dionysus.domain.api.AccountApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideOkHttpClient(sessionManager: SessionManager): OkHttpClient {
            return OkHttpClient.Builder()
                .authenticator(TokenAuthenticator(sessionManager))
                .addInterceptor(AuthInterceptor(sessionManager))
                .callTimeout(10L, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            val contentType = MediaType.get("application/json")
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(
                    "http://cv-dentistry.ru/"
                )
                .addConverterFactory(Json.asConverterFactory(contentType))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        @Provides
        @Singleton
        fun provideAccountApi(retrofit: Retrofit): AccountApi {
            return retrofit.create(AccountApi::class.java)
        }
    }

    @Binds
    abstract fun bindsSessionManager(sessionManager: SessionManagerImpl): SessionManager

}
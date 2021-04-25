package com.serma.dionysus.auth.domain

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.serma.dionysus.domain.api.AccountApi
import com.serma.dionysus.model.WebAppAuthenticateResponceDto
import com.serma.dionysus.utils.Result
import com.serma.dionysus.utils.getResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Inject

class RefreshTokenRemoteSource @Inject constructor() {
   suspend fun refreshToken(refreshToken: String): Result<WebAppAuthenticateResponceDto> {
        val contentType = MediaType.get("application/json")
        val retrofit = Retrofit.Builder()
            .baseUrl(
                "http://cv-dentistry.ru/"
            )
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        val api = retrofit.create(AccountApi::class.java)
        return getResponse { api.apiAccountRefreshRefreshTokenGet(refreshToken) }
    }
}
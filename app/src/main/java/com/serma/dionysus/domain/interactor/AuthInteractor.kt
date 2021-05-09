package com.serma.dionysus.domain.interactor

import com.serma.dionysus.domain.datasource.LoginRemoteSource
import com.serma.dionysus.model.WebAppAuthenticateResponceDto
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(private val remoteSource: LoginRemoteSource) {

    suspend fun login(
        username: String,
        password: String
    ): Flow<Result<WebAppAuthenticateResponceDto>> {
        return flow {
            delay(1000L)
            emit(
                Result.Success(
                    WebAppAuthenticateResponceDto(
                        id = "123123",
                        wrongCredentials = false,
                        username = username,
                        userRole = null,
                        token = "exampletokenasda",
                        expiresTime = 259200,
                        refreshToken = "refresgdsdasdasdasda"
                    )
                )
            )
        }
        //return remoteSource.login(WebAuthentocationRequestDto(username, password))
    }

    suspend fun registration(
        username: String,
        password: String
    ): Flow<Result<WebAppAuthenticateResponceDto>> {
        return flow {
            delay(1000L)
            emit(
                Result.Success(
                    WebAppAuthenticateResponceDto(
                        id = "123123",
                        wrongCredentials = false,
                        username = username,
                        userRole = null,
                        token = "exampletokenasda",
                        expiresTime = 259200,
                        refreshToken = "refresgdsdasdasdasda"
                    )
                )
            )
        }
    }

    suspend fun logout(
        token: String
    ): Flow<Result<Boolean>> {
        return flow { true }
    }
}
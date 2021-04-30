package com.serma.dionysus.domain.interactor

import com.serma.dionysus.domain.datasource.LoginRemoteSource
import com.serma.dionysus.model.WebAppAuthenticateResponceDto
import com.serma.dionysus.model.WebAuthentocationRequestDto
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginInteractor @Inject constructor(private val remoteSource: LoginRemoteSource) {
    suspend fun login(
        username: String,
        password: String
    ): Flow<Result<WebAppAuthenticateResponceDto>> {
        return remoteSource.login(WebAuthentocationRequestDto(username, password))
    }

    suspend fun logout(
        token: String
    ): Flow<Result<Boolean>>{
        return flow { true }
    }
}
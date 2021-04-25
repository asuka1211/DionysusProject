package com.serma.dionysus.domain.datasource

import com.serma.dionysus.domain.api.AccountApi
import com.serma.dionysus.model.WebAppAuthenticateResponceDto
import com.serma.dionysus.model.WebAppAuthenticateResponceDtoWebAppResponseWithEntityDto
import com.serma.dionysus.model.WebAuthentocationRequestDto
import com.serma.dionysus.utils.Result
import com.serma.dionysus.utils.getResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRemoteSource @Inject constructor(private val api: AccountApi) {
    suspend fun login(webAuthentocationRequestDto: WebAuthentocationRequestDto): Flow<Result<WebAppAuthenticateResponceDto>> {
        return flow {
            emit(
                getResponse {
                    api.apiAccountLoginPost(webAuthentocationRequestDto)
                }
            )
        }.flowOn(Dispatchers.IO)
    }
}
package com.serma.dionysus.domain.datasource

import com.serma.auth.domain.api.AccountApi
import com.serma.auth.model.WebAuthentocationRequestDto
import javax.inject.Inject

class LoginRemoteSource @Inject constructor(private val api: AccountApi) {
    suspend fun login(webAuthentocationRequestDto: WebAuthentocationRequestDto) {
        api.apiAccountLoginPost(webAuthentocationRequestDto)
    }
}
package com.serma.dionysus.auth.domain

import com.serma.dionysus.domain.api.AccountApi

class RefreshTokenRemoteSource constructor(private val api: AccountApi) {
    fun refreshToken(refreshToken: String): String {
       // return api.apiAccountRefreshRefreshTokenGet(refreshToken)
        return ""
    }
}
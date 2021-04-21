package com.serma.dionysus.auth

import com.serma.dionysus.auth.manager.SessionManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val sessionManager: SessionManager) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        sessionManager.updateToken()?.let {
           return response
                .request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
        }

        return response.request()
    }

}
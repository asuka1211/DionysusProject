package com.serma.dionysus.auth.manager

interface SessionManager {
    fun isExpired(): Boolean
    fun getToken(): String?
    fun getRefreshToken(): String?
    fun saveTokenData(token: String, refreshToken: String, timeLeft: Long)
    fun updateToken(): String?
}
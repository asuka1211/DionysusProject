package com.serma.dionysus.auth.manager

import android.content.Context
import android.content.SharedPreferences
import com.serma.dionysus.R
import com.serma.dionysus.auth.domain.RefreshTokenRemoteSource
import java.util.*

class SessionManagerImpl constructor(
    private val remoteSource: RefreshTokenRemoteSource,
    context: Context
) : SessionManager {

    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    private val calendar = Calendar.getInstance()

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_TOKEN_REFRESH = "user_token_refresh"
        const val TIME_SAVED = "time_saved"
    }

    override fun isExpired(): Boolean {
        val timeSaved = prefs.getLong(TIME_SAVED, 0L)
        return calendar.timeInMillis / 1000 >= timeSaved
    }

    override fun getToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    override fun getRefreshToken(): String? {
        return prefs.getString(USER_TOKEN_REFRESH, null)
    }

    override fun saveTokenData(token: String, refreshToken: String, timeLeft: Long) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.putString(USER_TOKEN_REFRESH, refreshToken)
        val time = calendar.timeInMillis / 1000
        editor.putLong(TIME_SAVED, time + timeLeft)
        editor.apply()
    }

    override fun updateToken(): String? {
        var refreshToken: String? = null
        getRefreshToken()?.let {
            refreshToken = remoteSource.refreshToken(it)
            saveTokenData(it, it, 213) //TODO ПОМЕНЯТЬ С ГОТОВЫМ БЕКОМ
        }
        return refreshToken
    }
}
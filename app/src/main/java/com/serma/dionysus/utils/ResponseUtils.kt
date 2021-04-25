package com.serma.dionysus.utils

import com.serma.dionysus.model.WebAppResponseDto
import retrofit2.Response

suspend fun <T> getResponse(
    request: suspend () -> Response<WebAppResponseDto<T>>
): Result<T> {
    return try {
        val result = request.invoke()
        if (result.isSuccessful) {
            Result.Success(result.body()?.result!!)
        } else {
            Result.Error(result.body()?.errors!!.first().message!!)
        }
    } catch (e: Throwable) {
        Result.Error("Unknown Error")
    }
}

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val message: String) : Result<T>()
}
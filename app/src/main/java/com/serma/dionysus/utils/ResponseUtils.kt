package com.serma.dionysus.utils

import retrofit2.Response

//suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
//    return try {
//        val result = request.invoke()
//        if (result.isSuccessful) {
//            return Result.Success(result.body())
//        } else {
//            val errorResponse = ErrorUtils.parseError(result, retrofit)
//            Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
//        }
//    } catch (e: Throwable) {
//        Result.Error("Unknown Error")
//    }
//}

sealed class Result <T>{
   data class Success<T>(val data: T): Result<T>()
   data class Error(val message: String): Result<String>()
}
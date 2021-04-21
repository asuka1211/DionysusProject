package com.serma.dionysus.auth

import retrofit2.http.*
import retrofit2.Response




interface AccountApi {

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param refreshToken  
     * @return [Unit]
     */
    @GET("api/Account/refresh/{refreshToken}")
    fun apiAccountRefreshRefreshTokenGet(@Path("refreshToken") refreshToken: String): Response<Unit>


}

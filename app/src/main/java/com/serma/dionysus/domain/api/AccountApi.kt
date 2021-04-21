package com.serma.auth.domain.api

import com.serma.auth.model.*
import retrofit2.Response
import retrofit2.http.*

interface AccountApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param linkToken  
     * @return [Unit]
     */
    @GET("api/Account/approve/{linkToken}")
    suspend fun apiAccountApproveLinkTokenGet(@Path("linkToken") linkToken: kotlin.String): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param webAuthentocationRequestDto  (optional)
     * @return [Unit]
     */
    @POST("api/Account/login")
    suspend fun apiAccountLoginPost(@Body webAuthentocationRequestDto: WebAuthentocationRequestDto? = null): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @return [Unit]
     */
    @POST("api/Account/logout")
    suspend fun apiAccountLogoutPost(): Response<Unit>

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
    suspend fun apiAccountRefreshRefreshTokenGet(@Path("refreshToken") refreshToken: java.util.UUID): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param webAppRegistrationRequestDto  (optional)
     * @return [Unit]
     */
    @POST("api/Account/registration")
    suspend fun apiAccountRegistrationPost(@Body webAppRegistrationRequestDto: WebAppRegistrationRequestDto? = null): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param webAppRegistrationRequestDto  (optional)
     * @return [Unit]
     */
    @POST("api/Account/registrationbyinvite")
    suspend fun apiAccountRegistrationbyinvitePost(@Body webAppRegistrationRequestDto: WebAppRegistrationRequestDto? = null): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param webAppResendEmailDto  (optional)
     * @return [Unit]
     */
    @POST("api/Account/resend")
    suspend fun apiAccountResendPost(@Body webAppResendEmailDto: WebAppResendEmailDto? = null): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param webAppRestorePasswordDto  (optional)
     * @return [Unit]
     */
    @POST("api/Account/restore")
    suspend fun apiAccountRestorePost(@Body webAppRestorePasswordDto: WebAppRestorePasswordDto? = null): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param searchQuery  
     * @return [Unit]
     */
    @GET("api/Account/search/{searchQuery}")
    suspend fun apiAccountSearchSearchQueryGet(@Path("searchQuery") searchQuery: kotlin.String): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @return [Unit]
     */
    @GET("api/Account/self")
    suspend fun apiAccountSelfGet(): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param webAppUpdateUserRequestDto  (optional)
     * @return [Unit]
     */
    @PUT("api/Account/update")
    suspend fun apiAccountUpdatePut(@Body webAppUpdateUserRequestDto: WebAppUpdateUserRequestDto? = null): Response<Unit>

}

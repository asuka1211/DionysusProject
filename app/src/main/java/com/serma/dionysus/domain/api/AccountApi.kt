package com.serma.dionysus.domain.api

import com.serma.dionysus.model.*
import retrofit2.Response
import retrofit2.http.*

interface AccountApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param linkToken  
     * @return [WebAppResponseDto]
     */


    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     *  - 403: Forbidden
     * 
     * @param webAuthentocationRequestDto  (optional)
     * @return [WebAppAuthenticateResponceDtoWebAppResponseWithEntityDto]
     */
    @POST("api/Account/login")
    suspend fun apiAccountLoginPost(@Body webAuthentocationRequestDto: WebAuthentocationRequestDto? = null): Response<WebAppResponseDto<WebAppAuthenticateResponceDto>>

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
     *  - 400: Bad Request
     *  - 403: Forbidden
     * 
     * @param refreshToken  
     * @return [WebAppAuthenticateResponceDtoWebAppResponseWithEntityDto]
     */
    @GET("api/Account/refresh/{refreshToken}")
    suspend fun apiAccountRefreshRefreshTokenGet(@Path("refreshToken") refreshToken: String): Response<WebAppResponseDto<WebAppAuthenticateResponceDto>>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param webAppRegistrationRequestDto  (optional)
     * @return [WebAppResponseDto]
     */


    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param webAppRegistrationRequestDto  (optional)
     * @return [WebAppResponseDto]
     */


    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param webAppResendEmailDto  (optional)
     * @return [WebAppResponseDto]
     */


    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param webAppRestorePasswordDto  (optional)
     * @return [WebAppResponseDto]
     */


    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param searchQuery  
     * @return [WebAppUserTableItemDtoWebAppTableDtoWebAppUserTableItemDtoWebAppResponseWithTableDto]
     */
    @GET("api/Account/search/{searchQuery}")
    suspend fun apiAccountSearchSearchQueryGet(@Path("searchQuery") searchQuery: kotlin.String): Response<WebAppUserTableItemDtoWebAppTableDtoWebAppUserTableItemDtoWebAppResponseWithTableDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @return [WebAppAuthUserResponseDtoWebAppResponseWithEntityDto]
     */
    @GET("api/Account/self")
    suspend fun apiAccountSelfGet(): Response<WebAppAuthUserResponseDtoWebAppResponseWithEntityDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param webAppUpdateUserRequestDto  (optional)
     * @return [WebAppResponseDto]
     */


}

package com.serma.dionysus.domain.api

import com.serma.dionysus.model.*
import retrofit2.Response
import retrofit2.http.*

interface EventApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param webAppEventDto  (optional)
     * @return [WebAppResponseDto]
     */
//    @POST("api/Event/create")
//    suspend fun apiEventCreatePost(@Body webAppEventDto: WebAppEventDto? = null): Response<WebAppResponseDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param id  
     * @return [WebAppResponseDto]
     */
//    @DELETE("api/Event/{id}")
//    suspend fun apiEventIdDelete(@Path("id") id: java.util.UUID): Response<WebAppResponseDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param id  
     * @param webAppEventDto  (optional)
     * @return [WebAppResponseDto]
     */
//    @PUT("api/Event/{id}")
//    suspend fun apiEventIdPut(@Path("id") id: java.util.UUID, @Body webAppEventDto: WebAppEventDto? = null): Response<WebAppResponseDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param invitePersonDto  (optional)
     * @return [WebAppResponseDto]
     */
//    @POST("api/Event/invite")
//    suspend fun apiEventInvitePost(@Body invitePersonDto: InvitePersonDto? = null): Response<WebAppResponseDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param id  
     * @return [WebAppEventDtoWebAppResponseWithEntityDto]
     */
    @GET("api/Event/item/{id}")
    suspend fun apiEventItemIdGet(@Path("id") id: java.util.UUID): Response<WebAppEventDtoWebAppResponseWithEntityDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param page  
     * @param pageSize  
     * @return [WebAppEventDtoWebAppTableDtoWebAppEventDtoWebAppResponseWithTableDto]
     */
    @GET("api/Event/items/{page}/{pageSize}")
    suspend fun apiEventItemsPagePageSizeGet(@Path("page") page: kotlin.Int, @Path("pageSize") pageSize: kotlin.Int): Response<WebAppEventDtoWebAppTableDtoWebAppEventDtoWebAppResponseWithTableDto>

}

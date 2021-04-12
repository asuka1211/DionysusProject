package org.openapi.example.api

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody


interface LocalizationApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param localeId  
     * @param fileId  
     * @return [Unit]
     */
    @POST("api/Localization/import/{localeId}/{fileId}")
    suspend fun apiLocalizationImportLocaleIdFileIdPost(@Path("localeId") localeId: java.util.UUID, @Path("fileId") fileId: java.util.UUID): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param id  
     * @return [Unit]
     */
    @GET("api/Localization/item/{id}")
    suspend fun apiLocalizationItemIdGet(@Path("id") id: java.util.UUID): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @return [Unit]
     */
    @GET("api/Localization/list")
    suspend fun apiLocalizationListGet(): Response<Unit>

}

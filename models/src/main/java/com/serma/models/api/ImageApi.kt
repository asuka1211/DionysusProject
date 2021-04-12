package org.openapi.example.api

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody


interface ImageApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param id  
     * @return [Unit]
     */
    @GET("api/Image/load/{id}")
    suspend fun apiImageLoadIdGet(@Path("id") id: java.util.UUID): Response<Unit>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param contentType  (optional)
     * @param contentDisposition  (optional)
     * @param length  (optional)
     * @param name  (optional)
     * @param fileName  (optional)
     * @return [Unit]
     */
    @Multipart
    @POST("api/Image/save")
    suspend fun apiImageSavePost(@Part("ContentType") contentType: kotlin.String, @Part("ContentDisposition") contentDisposition: kotlin.String, @Part("Length") length: kotlin.Long, @Part("Name") name: kotlin.String, @Part("FileName") fileName: kotlin.String): Response<Unit>

}

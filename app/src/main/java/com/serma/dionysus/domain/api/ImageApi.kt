package com.serma.dionysus.domain.api

import okhttp3.ResponseBody
import com.serma.dionysus.model.WebAppImageResponseDtoWebAppResponseWithEntityDto
import retrofit2.Response
import retrofit2.http.*

interface ImageApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param id  
     * @return [ResponseBody]
     */
    @GET("api/Image/load/{id}")
    suspend fun apiImageLoadIdGet(@Path("id") id: java.util.UUID): Response<ResponseBody>

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
     * @return [WebAppImageResponseDtoWebAppResponseWithEntityDto]
     */
    @Multipart
    @POST("api/Image/save")
    suspend fun apiImageSavePost(@Part("ContentType") contentType: kotlin.String, @Part("ContentDisposition") contentDisposition: kotlin.String, @Part("Length") length: kotlin.Long, @Part("Name") name: kotlin.String, @Part("FileName") fileName: kotlin.String): Response<WebAppImageResponseDtoWebAppResponseWithEntityDto>

}

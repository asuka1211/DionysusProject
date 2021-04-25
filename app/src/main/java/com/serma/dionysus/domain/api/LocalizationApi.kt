package com.serma.dionysus.domain.api

import com.serma.dionysus.model.WebAppLocaleDtoWebAppResponseWithEntityDto
import com.serma.dionysus.model.WebAppLocaleTableItemDtoWebAppTableDtoWebAppLocaleTableItemDtoWebAppResponseWithTableDto
import com.serma.dionysus.model.WebAppResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LocalizationApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param localeId  
     * @param fileId  
     * @return [WebAppResponseDto]
     */
    @POST("api/Localization/import/{localeId}/{fileId}")
    suspend fun apiLocalizationImportLocaleIdFileIdPost(@Path("localeId") localeId: java.util.UUID, @Path("fileId") fileId: java.util.UUID): Response<WebAppResponseDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @param id  
     * @return [WebAppLocaleDtoWebAppResponseWithEntityDto]
     */
    @GET("api/Localization/item/{id}")
    suspend fun apiLocalizationItemIdGet(@Path("id") id: java.util.UUID): Response<WebAppLocaleDtoWebAppResponseWithEntityDto>

    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     *  - 400: Bad Request
     * 
     * @return [WebAppLocaleTableItemDtoWebAppTableDtoWebAppLocaleTableItemDtoWebAppResponseWithTableDto]
     */
    @GET("api/Localization/list")
    suspend fun apiLocalizationListGet(): Response<WebAppLocaleTableItemDtoWebAppTableDtoWebAppLocaleTableItemDtoWebAppResponseWithTableDto>

}

package org.openapi.example.api

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody


interface ContactsApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: Success
     * 
     * @param page  
     * @param pageSize  
     * @return [Unit]
     */
    @GET("api/Contacts/items/{page}/{pageSize}")
    suspend fun apiContactsItemsPagePageSizeGet(@Path("page") page: kotlin.Int, @Path("pageSize") pageSize: kotlin.Int): Response<Unit>

}

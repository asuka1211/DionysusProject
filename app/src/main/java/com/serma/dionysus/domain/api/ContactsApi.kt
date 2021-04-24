package com.serma.dionysus.domain.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


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

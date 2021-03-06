/**
* Party maker API
* No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
*
* The version of the OpenAPI document: v1
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapi.example.model

import org.openapi.example.model.ETypeEvent
import org.openapi.example.model.WebAppUserEventDto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * 
 * @param id 
 * @param name 
 * @param date 
 * @param address 
 * @param latitude 
 * @param longitude 
 * @param typeEvent 
 * @param totalBudget 
 * @param participaties 
 */

data class WebAppEventDto (
    @SerializedName("id")
    val id: java.util.UUID? = null,
    @SerializedName("name")
    val name: kotlin.String? = null,
    @SerializedName("date")
    val date: java.time.OffsetDateTime? = null,
    @SerializedName("address")
    val address: kotlin.String? = null,
    @SerializedName("latitude")
    val latitude: kotlin.Double? = null,
    @SerializedName("longitude")
    val longitude: kotlin.Double? = null,
    @SerializedName("typeEvent")
    val typeEvent: ETypeEvent? = null,
    @SerializedName("totalBudget")
    val totalBudget: kotlin.Double? = null,
    @SerializedName("participaties")
    val participaties: kotlin.collections.List<WebAppUserEventDto>? = null
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }

}


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


import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * 
 * @param email 
 * @param eventName 
 */

data class InvitePersonDto (
    @SerializedName("email")
    val email: kotlin.String? = null,
    @SerializedName("eventName")
    val eventName: kotlin.String? = null
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }

}


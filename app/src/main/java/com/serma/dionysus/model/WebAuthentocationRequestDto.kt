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
package com.serma.auth.model


import kotlinx.serialization.SerialName
import java.io.Serializable

/**
 * 
 * @param email 
 * @param password 
 */

data class WebAuthentocationRequestDto (
    @SerialName("email")
    val email: kotlin.String? = null,
    @SerialName("password")
    val password: kotlin.String? = null
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }

}


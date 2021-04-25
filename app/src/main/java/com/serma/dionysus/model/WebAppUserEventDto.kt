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
package com.serma.dionysus.model

import kotlinx.serialization.SerialName
import java.io.Serializable

/**
 * 
 * @param userId 
 * @param role 
 * @param name 
 */

data class WebAppUserEventDto (
    @SerialName("userId")
    val userId: java.util.UUID? = null,
    @SerialName("role")
    val role: EUserEventRole? = null,
    @SerialName("name")
    val name: kotlin.String? = null
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }

}


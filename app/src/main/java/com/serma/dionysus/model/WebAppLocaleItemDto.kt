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
 * @param id 
 * @param key 
 * @param value 
 */

data class WebAppLocaleItemDto (
    @SerialName("id")
    val id: java.util.UUID? = null,
    @SerialName("key")
    val key: kotlin.String? = null,
    @SerialName("value")
    val value: kotlin.String? = null
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }

}


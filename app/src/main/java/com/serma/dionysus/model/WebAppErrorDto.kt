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
 * @param message 
 * @param fieldName 
 */

data class WebAppErrorDto (
    @SerialName("message")
    val message: kotlin.String? = null,
    @SerialName("fieldName")
    val fieldName: kotlin.String? = null
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }

}


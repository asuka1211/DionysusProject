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
 * @param isSuccess 
 * @param isTimeout 
 * @param isNotFound 
 * @param message 
 * @param errors 
 * @param isInvalid 
 * @param result 
 */

data class WebAppAuthenticateResponceDtoWebAppResponseWithEntityDto (
    @SerialName("isSuccess")
    val isSuccess: kotlin.Boolean? = null,
    @SerialName("isTimeout")
    val isTimeout: kotlin.Boolean? = null,
    @SerialName("isNotFound")
    val isNotFound: kotlin.Boolean? = null,
    @SerialName("message")
    val message: kotlin.String? = null,
    @SerialName("errors")
    val errors: kotlin.collections.List<WebAppErrorDto>? = null,
    @SerialName("isInvalid")
    val isInvalid: kotlin.Boolean? = null,
    @SerialName("result")
    val result: WebAppAuthenticateResponceDto? = null
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }

}


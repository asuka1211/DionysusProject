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

/**
* 
* Values: _0,_1
*/

enum class ETypeEvent(val value: kotlin.Int) {


    @SerializedName(value = "0")
    0(0),

    @SerializedName(value = "1")
    1(1);


    /**
    This override toString avoids using the enum var name and uses the actual api value instead.
    In cases the var name and value are different, the client would send incorrect enums to the server.
    **/
    override fun toString(): String {
        return value.toString()
    }
}


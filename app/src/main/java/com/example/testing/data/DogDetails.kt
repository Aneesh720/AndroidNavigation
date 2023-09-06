package com.example.testing.data

data class DogDetails(
    val message: String,
    val status: String,

)

fun fromJson(json: Map<String,String>): DogDetails {
    return DogDetails(
        message=json["message"].toString(),
        status =json["status"].toString(),
    )
}


fun toMap(dogDetails: DogDetails): Map<String,String>{
    return mapOf("message" to dogDetails.message )
}

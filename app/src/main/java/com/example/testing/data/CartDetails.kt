package com.example.testing.data

data class CartDetails(
    val image: String,
    val price: Double,
)


fun cartFromJson(json: Map<String, String>): CartDetails {
    return CartDetails(
        image = json["image"].toString(),
        price = json["price"].toString().toDouble(),
    )
}


fun cartToMap(cartDetails: CartDetails): Map<String, String> {
    return mapOf("image" to cartDetails.image, "price" to cartDetails.price.toString())
}

package com.devlade.foodjunks

class RecommendedHelper(
    private var image: Int,
    private var name: String,
    private var description: String,
    private var price: String
) {
    fun getImage(): Int{
        return image
    }
    fun getName(): String{
        return name
    }
    fun getDescription(): String{
        return description
    }
    fun getPrice(): String{
        return price
    }
}
package com.devlade.foodjunks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class CategoriesHelper(
    private var image: Int,
    private var title: String,
    private var rating: String,
    private var duration: String,
    private var price: String
): Parcelable {
    fun getImage(): Int{
        return image
    }
    fun getTitle(): String{
        return title
    }
    fun getRating(): String{
        return rating
    }
    fun getDuration(): String{
        return duration
    }
    fun getPrice(): String{
        return price
    }
}
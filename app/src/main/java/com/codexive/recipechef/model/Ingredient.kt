package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Ingredient(
    @field:Json(name="ingredientName") val ingredientName: String = "",
    @field:Json(name="category") val category: String = "",
    @field:Json(name="quantity") val quantity: String? = "",
    @field:Json(name="measurement") val measurement: String? = "",
    @field:Json(name="measurement") val notes: String? = ""
    ) : Serializable
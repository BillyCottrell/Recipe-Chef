package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Ingredient(
    @field:Json(name="ingredient") val ingredientName: String,
    var category: String,
    @field:Json(name="hoeveelheid") val quantity: String? = "",
    @field:Json(name="soort") val measurement: String? = "",
    var notes: String? = ""
    ) : Serializable
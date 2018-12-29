package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Ingredient(
    @field:Json(name="ingredient") val ingredient: String,
    var category: String,
    @field:Json(name="hoeveelheid") val hoeveelheid: String? = "",
    @field:Json(name="soort") val soort: String? = ""
    ) : Serializable
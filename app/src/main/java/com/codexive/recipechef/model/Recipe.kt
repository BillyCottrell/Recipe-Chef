package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Recipe(
    @field:Json(name = "naam") val name: String,
    @field:Json(name = "basisIngredient") val basicIngredient: Boolean,
    @field:Json(name = "beschrijving") val description: String,
    @field:Json(name = "aantalPersonen") val servings: Int,
    var user: String,
    @field:Json(name = "ingredienten") val ingredients: Array<Ingredient>,
    var preparationTime: Int,
    var preparationMethod: Array<String>,
    var image: String = "",
    var views: Int = 0,
    var id: String?=""
) : Serializable
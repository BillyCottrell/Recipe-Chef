package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Recipe(
    @field:Json(name="name") val name: String = "",
    @field:Json(name="description") val description: String = "",
    @field:Json(name="servings") val servings: Int = 0,
    @field:Json(name="ingredients") val ingredients: ArrayList<Ingredient> = arrayListOf(),
    @field:Json(name="preparationTime") val preparationTime: Int = 0,
    @field:Json(name="preparationMethod") val preparationMethod: ArrayList<String> = arrayListOf(),
    @field:Json(name="image") val image: String = "",
    @field:Json(name="views") var views: Int = 0,
    @field:Json(name="id") var id: String?=""
) : Serializable
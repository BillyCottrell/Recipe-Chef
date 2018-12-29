package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Recipe(
    @field:Json(name="naam") val naam: String,
    @field:Json(name="basisIngredient") val basisIngredient: Boolean,
    @field:Json(name="beschrijving") val beschrijving: String,
    @field:Json(name="aantalPersonen") val aantalPersonen:Int,
    var user: String,
    @field:Json(name="ingredienten") val ingredienten: Array<Ingredient>,
    var stappen: Array<String>,
    var image: String = "",
    var notes: String = "",
    var bereidingstijd: Int = 0
) : Serializable
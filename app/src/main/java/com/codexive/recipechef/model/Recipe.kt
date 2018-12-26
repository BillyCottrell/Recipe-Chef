package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Recipe(
    @field:Json(name="aantalPersonen") val aantalPersonen:Int,
    @field:Json(name="basisIngredient") val basisIngredient: Boolean,
    @field:Json(name="beschrijving") val beschrijving: String,
    @field:Json(name="ingredienten") val ingredienten: Array<Ingredient>?,
    @field:Json(name="naam") val naam: String,
    var stappen: Array<String>) : Serializable
package com.codexive.recipechef.model

import com.squareup.moshi.Json
import java.io.Serializable

open class Ingredient(
    @field:Json(name="hoeveelheid_Vast") val hoeveelheidVast: Number,
    @field:Json(name="hoeveelheid_Volume") val hoeveelheidVolume: Number,
    @field:Json(name="ingredient") val ingredient: Recipe,
    @field:Json(name="soort_Vast") val soortVast: String,
    @field:Json(name="soort_Volume") val soortVolume: String,
    @field:Json(name="vast") val vast: Boolean
    ) : Serializable
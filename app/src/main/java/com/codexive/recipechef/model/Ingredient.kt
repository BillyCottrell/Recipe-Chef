package com.codexive.recipechef.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import java.io.Serializable


data class Ingredient(
    @field:Json(name="ingredientName")
    var ingredientName: String = "",
    @field:Json(name="category")
    var category: String = "",
    @field:Json(name="quantity")
    var quantity: String? = "",
    @field:Json(name="measurement")
    var measurement: String? = "",
    @field:Json(name="notes")
    var notes: String? = ""
    ) : Serializable
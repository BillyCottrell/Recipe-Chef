package com.codexive.recipechef.model

import android.arch.persistence.room.*
import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import java.io.Serializable
@Entity(tableName = "recipe_table")
data class Recipe(
    @ColumnInfo(name="name")
    @field:Json(name="name")
    var name: String = "",

    @ColumnInfo(name="description")
    @field:Json(name="description")
    var description: String = "",

    @ColumnInfo(name="servings")
    @field:Json(name="servings")
    var servings: Int = 0,

    @field:Json(name="ingredients")
    var ingredients: List<Ingredient> = arrayListOf(),

    @ColumnInfo(name="preparationTime")
    @field:Json(name="preparationTime")
    var preparationTime: Int = 0,

    @ColumnInfo(name="preparationMethod")
    @field:Json(name="preparationMethod")
    var preparationMethod: List<String> = arrayListOf(),

    @ColumnInfo(name="image")
    @field:Json(name="image")
    var image: String = "",

    @ColumnInfo(name="views")
    @field:Json(name="views")
    var views: Int = 0,
    @PrimaryKey
    @ColumnInfo(name="id")
    @field:Json(name="id")
    var id: String
) : Serializable
package com.codexive.recipechef.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(val id: Int, var naam: String, var description: String,var personen:Int, var stappen: Array<String>, var ingredienten: Array<Ingredient>) : Parcelable
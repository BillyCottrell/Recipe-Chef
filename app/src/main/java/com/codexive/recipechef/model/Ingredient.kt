package com.codexive.recipechef.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(val id: Int, var naam: String, var  hoeveelheid: Double, var eenheid : String) : Parcelable
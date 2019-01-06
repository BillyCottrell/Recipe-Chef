package com.codexive.recipechef.utils

import com.codexive.recipechef.model.Ingredient

interface DialogDataSender {
    fun passIngredient(ing : Ingredient)
    fun passPreparationMethod(prep: String)
}
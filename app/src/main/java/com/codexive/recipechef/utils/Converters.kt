package com.codexive.recipechef.utils

import android.arch.persistence.room.TypeConverter
import com.codexive.recipechef.model.Ingredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



public class Converters {
    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String>>() {
        }.type)
    }
    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return Gson().toJson(listOfString)
    }
    @TypeConverter
    fun saveIngredients(listOfIngredients:List<Ingredient>):String{
        return Gson().toJson(listOfIngredients)
    }
    @TypeConverter
    fun restoreIngredients(listofIngredients: String): List<Ingredient>{
        return Gson().fromJson(listofIngredients, object :TypeToken<List<Ingredient>>(){
        }.type)
    }
}
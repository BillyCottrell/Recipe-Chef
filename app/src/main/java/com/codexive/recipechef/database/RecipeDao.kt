package com.codexive.recipechef.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.codexive.recipechef.model.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * from recipe_table ORDER BY name ASC")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Insert
    fun insert(recipe: Recipe)

    @Update
    fun update(recipe: Recipe)

}
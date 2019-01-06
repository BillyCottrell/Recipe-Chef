package com.codexive.recipechef.model

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.codexive.recipechef.database.RecipeDao
import org.jetbrains.anko.doAsync

class RecipeRepository(private val recipeDao: RecipeDao){
    val recipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    @WorkerThread
    fun insert(recipe: Recipe){
        doAsync {
            recipeDao.insert(recipe)
        }
    }
    @WorkerThread
    fun update(recipe: Recipe){
        doAsync {
            recipeDao.update(recipe)
        }
    }
}
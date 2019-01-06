package com.codexive.recipechef.injection.module

import android.app.Application
import android.content.Context
import com.codexive.recipechef.database.RecipeDao
import com.codexive.recipechef.database.RecipeDatabase
import com.codexive.recipechef.model.RecipeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideRecipeRepository(recipeDao: RecipeDao): RecipeRepository {
        return RecipeRepository(recipeDao)
    }

    @Provides
    @Singleton
    internal fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao {
        return recipeDatabase.recipeDao()
    }

    @Provides
    @Singleton
    internal fun provideRecipeDatabase(context: Context): RecipeDatabase {
        return RecipeDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }
}
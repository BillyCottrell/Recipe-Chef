package com.codexive.recipechef.database

import android.arch.persistence.room.*
import android.content.Context
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.utils.Converters

@Database(entities = [Recipe::class],version = 1)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase?=null
        const val DATABASE_NAME = "Recipe_database"

        fun getDatabase(context: Context): RecipeDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
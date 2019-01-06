package com.codexive.recipechef.injection.component

import com.codexive.recipechef.App
import com.codexive.recipechef.injection.module.NetworkModule
import com.codexive.recipechef.ui.RecipeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface DatabaseComponent {
    fun inject(app: App)
    fun inject(recipeViewModel: RecipeViewModel)
}
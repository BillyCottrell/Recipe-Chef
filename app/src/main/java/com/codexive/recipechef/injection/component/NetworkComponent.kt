package com.codexive.recipechef.injection.component

import com.codexive.recipechef.injection.module.NetworkModule
import com.codexive.recipechef.ui.RecipeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[NetworkModule::class])
interface NetworkComponent {
    fun inject(recViewModel: RecipeViewModel)
}
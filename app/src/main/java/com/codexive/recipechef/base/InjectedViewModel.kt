package com.codexive.recipechef.base
import android.arch.lifecycle.ViewModel
import com.codexive.recipechef.injection.component.DaggerNetworkComponent
import com.codexive.recipechef.injection.component.DatabaseComponent
import com.codexive.recipechef.injection.component.NetworkComponent
import com.codexive.recipechef.injection.module.DatabaseModule
import com.codexive.recipechef.injection.module.NetworkModule
import com.codexive.recipechef.ui.RecipeViewModel

abstract class InjectedViewModel : ViewModel() {
    private val injector: NetworkComponent = DaggerNetworkComponent
        .builder()
        .networkModule(NetworkModule)
        .build()
    init {
        inject()
    }
    private fun inject() {
        when (this) {
            is RecipeViewModel -> injector.inject(this)
        }
    }
}
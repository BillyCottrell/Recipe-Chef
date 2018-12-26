/*package com.codexive.recipechef.ui

import android.arch.lifecycle.MutableLiveData
import com.codexive.recipechef.base.InjectedViewModel
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.network.RecipeAPI
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeViewModel : InjectedViewModel(){
    val rawRecipe= MutableLiveData<Array<Recipe>>()
    @Inject
    lateinit var recipeApi : RecipeAPI

    private var subscription: Disposable

    init {
        subscription = recipeApi.getRecipes("recipes")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveRecipeStart() }
            .doOnTerminate { onRetrieveRecipeFinish() }
            .subscribe(
                { result -> onRetrieveRecipeSucces(result) },
                { error -> onRetrieveRecipeError(error) }
            )
    }

    private fun onRetrieveRecipeError(error: Throwable) {
        //Currently requests fail silently, which isn't great for the user.
        //It would be better to show a Toast, or maybe make a TextView visible with the error message.
        Logger.e(error.message!!)
    }

    private fun onRetrieveRecipeSucces(result: Array<Recipe>) {

        rawRecipe.value = result
        Logger.i(result.toString())
    }

    private fun onRetrieveRecipeFinish() {
        Logger.i("Finished retrieving recipes")
    }

    private fun onRetrieveRecipeStart() {
        Logger.i("Started retrieving recipes")
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}*/
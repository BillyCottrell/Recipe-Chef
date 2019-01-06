package com.codexive.recipechef.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import com.codexive.recipechef.R
import com.codexive.recipechef.holder.RecipeHolder
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.utils.RecyclerViewClickListener

class RecipeAdapter(private val context:Context, private var recipes: ArrayList<Recipe>, private val recipeListener: RecyclerViewClickListener) : RecyclerView.Adapter<RecipeHolder>(){

    internal fun setRecipes(recipes:ArrayList<Recipe>){
        this.recipes = recipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item,parent, false)
        return RecipeHolder(view, recipeListener)
    }
    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        val recipe: Recipe = this.recipes[position]
        holder.setDetails(recipe)
    }

    fun filter(ingredients: ArrayList<String>){
        val filteredList = arrayListOf<Recipe>()
        for (recipe in recipes) {
            val containsAll = arrayListOf<Boolean>()
            ingredients.forEach {
                var contains = false
                for (ingredient in recipe.ingredients) {
                    if(ingredient.ingredientName.contains(it)){
                        Log.d("ingredientName",String.format(ingredient.ingredientName))
                        contains=true
                    }
                }
                if(contains){
                    containsAll.add(contains)
                }
            }
            if(containsAll.size==ingredients.size){
                filteredList.add(recipe)
            }
        }
        this.recipes = filteredList
        this.notifyDataSetChanged()
    }
}
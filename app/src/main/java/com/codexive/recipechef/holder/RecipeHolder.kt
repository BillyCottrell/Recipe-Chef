package com.codexive.recipechef.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.codexive.recipechef.R
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.utils.RecyclerViewClickListener

class RecipeHolder(itemView: View, listener: RecyclerViewClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val itemListener: RecyclerViewClickListener
    private val imgRecipe: ImageButton
    private val txtName: TextView

    private lateinit var recipe: Recipe

    init{
        super.itemView
        itemListener = listener
        imgRecipe = itemView.findViewById(R.id.recipeImagebtn)
        txtName = itemView.findViewById(R.id.txtName)
        itemView.setOnClickListener(this)
    }
    fun setDetails(recipe: Recipe){
        this.recipe = recipe
        txtName.text = recipe.name
        imgRecipe.setImageResource(imgRecipe.resources.getIdentifier("keldermanlunch1", "drawable", "com.codexive.recipechef"))
    }

    override fun onClick(v: View) {
        //itemListener.recyclerViewListClicked(recipe)
    }

}
package com.codexive.recipechef.ui

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.codexive.recipechef.base.InjectedViewModel
import com.codexive.recipechef.model.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class RecipeViewModel : InjectedViewModel(){
    @Inject
    lateinit var instance: FirebaseDatabase

    /*@Inject
    lateinit var firebaseAuth: FirebaseAuth*/

    var recipeList: MutableLiveData<ArrayList<Recipe>> = MutableLiveData()
    var recipes:ArrayList<Recipe> = arrayListOf()
    init{
        getData()
    }
    fun getData(){
        val recipeRef = instance.getReference("recipes")
        val recipeListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                val recipe = snapshot.getValue(Recipe::class.java)
                if(recipe!=null){
                    recipes.add(recipe)
                    recipeList.postValue(recipes)
                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        }
        recipeRef.addChildEventListener(recipeListener)
    }
    fun addRecipe(recipe:Recipe){
        val ref = instance.getReference("recipes")
        val key = ref.push().key
        recipe.id = key.toString()
        ref.child(recipe.id!!).setValue(recipe)
    }
}
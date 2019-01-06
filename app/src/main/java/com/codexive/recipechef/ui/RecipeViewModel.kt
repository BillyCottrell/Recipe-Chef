package com.codexive.recipechef.ui

import android.arch.lifecycle.MutableLiveData
import com.codexive.recipechef.base.InjectedViewModel
import com.codexive.recipechef.model.Recipe
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject



class RecipeViewModel : InjectedViewModel(){
    @Inject
    lateinit var instance: FirebaseDatabase

    var recipeList: MutableLiveData<ArrayList<Recipe>> = MutableLiveData()
    var recipes:ArrayList<Recipe> = arrayListOf()
    init{
        getData()
    }
    fun getData(){
        val recipeRef = instance.getReference("recipes")
        recipeRef.keepSynced(true)
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
        val ref = instance.reference.child("recipes")
        val key = ref.push().key
        recipe.id = key.toString()
        ref.child(key.toString()).setValue(recipe)
    }
    fun updateRecipe(recipe:Recipe){
        val ref = instance.reference.child("recipes").child(recipe.id!!)
        val recipeUpdates = HashMap<String, Any>()
        recipeUpdates["views"] = recipe.views
        ref.updateChildren(recipeUpdates)
    }
}
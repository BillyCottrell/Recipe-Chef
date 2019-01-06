package com.codexive.recipechef.activities

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.codexive.recipechef.R
import com.codexive.recipechef.fragments.*
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.ui.RecipeViewModel
import com.google.firebase.database.*
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    LeftoversFragment.OnFragmentInteractionListener,
    RecipeListFragment.OnFragmentInteractionListener,
    RecipeFragment.OnFragmentInteractionListener,
    MyRecipesFragment.OnFragmentInteractionListener, AddRecipeFragment.OnFragmentInteractionListener{

    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.list -> {
                    viewpager_main.setCurrentItem(BaseFragment.LIST, false)
                    true
                }
                R.id.myRecipes ->{
                    viewpager_main.setCurrentItem(BaseFragment.MYRECIPES, false)
                    true
                }
                R.id.leftovers -> {
                    viewpager_main.setCurrentItem(BaseFragment.LEFTOVERS, false)
                    true
                }
                else -> {
                    viewpager_main.setCurrentItem(BaseFragment.LIST, false)
                    true
                }
            }
        }
        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when (p0) {
                    BaseFragment.LIST -> return RecipeListFragment.newInstance(true, "","","")
                    BaseFragment.MYRECIPES -> return MyRecipesFragment.newInstance()
                    BaseFragment.LEFTOVERS -> return LeftoversFragment.newInstance()
                }
                return RecipeListFragment()
            }

            override fun getCount(): Int {
                return 5
            }
        }
        viewpager_main?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    BaseFragment.LIST -> {
                        if (navigation.visibility == View.INVISIBLE) {
                            navigation.visibility = View.VISIBLE
                        }
                        navigation.selectedItemId = R.id.list

                    }
                    BaseFragment.MYRECIPES -> {
                        if(navigation.visibility == View.INVISIBLE){
                            navigation.visibility = View.VISIBLE
                        }
                        navigation.selectedItemId = R.id.myRecipes
                    }
                    BaseFragment.LEFTOVERS -> {
                        if (navigation.visibility == View.INVISIBLE) {
                            navigation.visibility = View.VISIBLE
                        }
                        navigation.selectedItemId = R.id.leftovers
                    }
                }
            }
        })
        backbutton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        navigation.setOnNavigationItemReselectedListener(null)
    }

    override fun createRecipeClicked() {
        onBackPressed()
    }

    override fun addRecipeClicked() {
        frame.removeAllViews()
        switchVisibility(true)
        appName.visibility = View.VISIBLE
        appName.text = getString(R.string.addRecipe)
        supportFragmentManager.beginTransaction().replace(R.id.frame, AddRecipeFragment.newInstance(), "addRecipe").commit()
    }

    override fun leftoversClicked(firstIng: String, secondIng: String, thirdIng: String) {
        switchVisibility(true)
        appName.text = getString(R.string.filtered_list)
        appName.visibility = View.VISIBLE
        Log.d("ingredient", String.format(firstIng))
        supportFragmentManager.beginTransaction().add(R.id.frame, RecipeListFragment.newInstance(false,firstIng, secondIng, thirdIng)).commit()
    }

    override fun ingredientClicked(recipe: Recipe) {
        switchVisibility(true)
        appName.text = getString(R.string.ingredients)
        appName.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction().add(R.id.frame, IngredientListFragment.newInstance(recipe), "ingredientList").addToBackStack("recipe").commit()
    }

    override fun preparationMethodClicked(recipe: Recipe) {
        switchVisibility(true)
        appName.text = getString(R.string.preparationMethod)
        appName.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction().add(R.id.frame, PreparationListFragment.newInstance(recipe), "preparationList").addToBackStack("recipe").commit()
    }

    override fun recipeClicked(recipe: Recipe) {
        frame.removeAllViews()
        recipe.views += 1
        viewModel.updateRecipe(recipe)
        switchVisibility(true)
        supportFragmentManager.beginTransaction().replace(R.id.frame,RecipeFragment.newInstance(recipe), "recipe").commit()
        appName.visibility = View.GONE
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && event!!.repeatCount <= 0 && viewpager_main.visibility==View.GONE) {
            Log.d("CDA", "onKeyDown Called")
            onBackPressed()
            return true
        } else {
            super.finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        Log.d("CDA", "onBackPressed Called")
        supportFragmentManager.popBackStack()
        val recipeFragment = supportFragmentManager.findFragmentByTag("recipe")
        if(recipeFragment != null && recipeFragment.isVisible){
            switchVisibility(true)
            appName.visibility = View.GONE
        }
        if(supportFragmentManager.backStackEntryCount <= 0){
            switchVisibility(false)
        }
    }

    private fun switchVisibility(showFrame:Boolean){
        if(showFrame){
            frame.visibility = View.VISIBLE
            viewpager_main.visibility = View.GONE
            navigation.visibility = View.GONE
            backbutton.visibility = View.VISIBLE
            logo.visibility = View.GONE
        } else{
            frame.visibility = View.GONE
            viewpager_main.visibility = View.VISIBLE
            navigation.visibility = View.VISIBLE
            backbutton.visibility = View.GONE
            logo.visibility = View.VISIBLE
            appName.text = getString(R.string.app_name)
            appName.visibility = View.VISIBLE
        }
    }

}

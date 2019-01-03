package com.codexive.recipechef.activities

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
import com.google.firebase.database.*
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    AccountFragment.OnFragmentInteractionListener,
    LeftoversFragment.OnFragmentInteractionListener,
    RecipeListFragment.OnFragmentInteractionListener,
    RecipeFragment.OnFragmentInteractionListener,
    MyRecipesFragment.OnFragmentInteractionListener{

    //private val database = FirebaseDatabase.getInstance()
    private var recipe: Recipe?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    override fun onStart() {
        super.onStart()
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.list -> {
                    viewpager_main.setCurrentItem(BaseFragment.LIST, false)
                    true
                }
                R.id.myRecipe ->{
                    viewpager_main.setCurrentItem(BaseFragment.MYRECIPES, false)
                    true
                }
                R.id.leftovers -> {
                    viewpager_main.setCurrentItem(BaseFragment.LEFTOVERS, false)
                    true
                }
                R.id.account -> {
                    viewpager_main.setCurrentItem(BaseFragment.ACCOUNT, false)
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
                    BaseFragment.LIST -> return RecipeListFragment.newInstance()
                    BaseFragment.MYRECIPES -> return MyRecipesFragment.newInstance()
                    BaseFragment.LEFTOVERS -> return LeftoversFragment.newInstance()
                    BaseFragment.ACCOUNT -> return AccountFragment.newInstance()
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
                        navigation.selectedItemId = R.id.myRecipe
                    }
                    BaseFragment.LEFTOVERS -> {
                        if (navigation.visibility == View.INVISIBLE) {
                            navigation.visibility = View.VISIBLE
                        }
                        navigation.selectedItemId = R.id.leftovers
                    }
                    BaseFragment.ACCOUNT -> {
                        if (navigation.visibility == View.INVISIBLE) {
                            navigation.visibility = View.VISIBLE
                        }
                        navigation.selectedItemId = R.id.account
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

    override fun myRecipesClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun accountClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leftoversClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun recipeClicked(recipe: Recipe) {
        bookmark.visibility = View.GONE
        like.visibility = View.GONE
        share.visibility = View.GONE
        appName.text = getString(R.string.ingredients)
        appName.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction().add(R.id.frame, IngredientListFragment.newInstance(recipe), "ingredientList").addToBackStack("recipe").commit()
    }

    override fun listClicked(recipe: Recipe) {
        recipe.views += 1
        frame.visibility = View.VISIBLE
        viewpager_main.visibility = View.GONE
        navigation.visibility = View.GONE
        bookmark.visibility = View.VISIBLE
        like.visibility = View.VISIBLE
        share.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().replace(R.id.frame,RecipeFragment.newInstance(recipe), "recipe").commit()
        backbutton.visibility = View.VISIBLE
        logo.visibility = View.GONE
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
        var recipeFragment = supportFragmentManager.findFragmentByTag("recipe")
        if(recipeFragment != null && recipeFragment.isVisible){
            appName.visibility = View.GONE
            bookmark.visibility = View.VISIBLE
            like.visibility = View.VISIBLE
            share.visibility = View.VISIBLE
        }
        if(!(supportFragmentManager.backStackEntryCount > 0)){
            frame.visibility = View.GONE
            viewpager_main.visibility = View.VISIBLE
            navigation.visibility = View.VISIBLE
            bookmark.visibility = View.GONE
            like.visibility = View.GONE
            share.visibility = View.GONE
            backbutton.visibility = View.GONE
            logo.visibility = View.VISIBLE
            appName.text = getString(R.string.app_name)
            appName.visibility = View.VISIBLE
        }
    }

}

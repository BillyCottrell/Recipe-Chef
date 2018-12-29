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
    PopularFragment.OnFragmentInteractionListener,
    AccountFragment.OnFragmentInteractionListener,
    LeftoversFragment.OnFragmentInteractionListener,
    ListFragment.OnFragmentInteractionListener, RecipeFragment.OnFragmentInteractionListener {


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
                R.id.popular -> {
                    viewpager_main.setCurrentItem(BaseFragment.POPULAR, false)
                    true
                }
                R.id.list -> {
                    viewpager_main.setCurrentItem(BaseFragment.LIST, false)
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
                    viewpager_main.setCurrentItem(BaseFragment.POPULAR, false)
                    true
                }
            }
        }
        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when (p0) {
                    BaseFragment.POPULAR -> {
                        return PopularFragment.newInstance()
                    }
                    BaseFragment.LIST -> {
                        return ListFragment.newInstance()
                    }
                    BaseFragment.LEFTOVERS -> return LeftoversFragment.newInstance()
                    BaseFragment.ACCOUNT -> return AccountFragment.newInstance()
                }
                return PopularFragment()
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
                    BaseFragment.POPULAR -> {
                        if (navigation.visibility == View.INVISIBLE) {
                            navigation.visibility = View.VISIBLE
                        }
                        navigation.selectedItemId = R.id.popular
                    }
                    BaseFragment.LIST -> {
                        if (navigation.visibility == View.INVISIBLE) {
                            navigation.visibility = View.VISIBLE
                        }
                        navigation.selectedItemId = R.id.list

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
    }

    override fun onStop() {
        super.onStop()
        navigation.setOnNavigationItemReselectedListener(null)
    }

    override fun popularClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun accountClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leftoversClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun recipeClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listClicked(recipe: Recipe) {
        viewpager_main.visibility = View.GONE
        navigation.visibility = View.GONE
        supportFragmentManager.beginTransaction().add(R.id.frame,RecipeFragment.newInstance(recipe), "recipe").addToBackStack("activity").commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && event!!.repeatCount <= 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event);
    }

    override fun onBackPressed() {
        Log.d("CDA", "onBackPressed Called")
        frame.removeAllViews()
        viewpager_main.visibility = View.VISIBLE
        navigation.visibility = View.VISIBLE
    }

}

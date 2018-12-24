package com.codexive.recipechef.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.codexive.recipechef.R
import com.codexive.recipechef.fragments.*
import com.codexive.recipechef.model.Recipe
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    PopularFragment.OnFragmentInteractionListener,
    AccountFragment.OnFragmentInteractionListener,
    LeftoversFragment.OnFragmentInteractionListener,
    ListFragment.OnFragmentInteractionListener{

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("TopicList")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.popular -> {
                    viewpager_main.currentItem = BaseFragment.POPULAR
                    true
                }
                R.id.list -> {
                    viewpager_main.currentItem = BaseFragment.LIST
                    true
                }
                R.id.leftovers -> {
                    viewpager_main.currentItem = BaseFragment.LEFTOVERS
                    true
                }
                R.id.account -> {
                    viewpager_main.currentItem = BaseFragment.ACCOUNT
                    true
                }
                else -> {
                    viewpager_main.currentItem = BaseFragment.POPULAR
                    true
                }
            }
        }
        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when (p0){
                    BaseFragment.POPULAR -> return PopularFragment.newInstance()
                    BaseFragment.LIST -> return  ListFragment.newInstance()
                    BaseFragment.LEFTOVERS -> return LeftoversFragment.newInstance()
                    BaseFragment.ACCOUNT -> return AccountFragment.newInstance()
                }
                return PopularFragment()
            }

            override fun getCount(): Int {
                return 4
            }
        }
        viewpager_main?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                when(position){
                    BaseFragment.POPULAR -> navigation.selectedItemId = R.id.popular
                    BaseFragment.LIST -> navigation.selectedItemId = R.id.list
                    BaseFragment.LEFTOVERS -> navigation.selectedItemId = R.id.leftovers
                    BaseFragment.ACCOUNT -> navigation.selectedItemId = R.id.account
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

    override fun listClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

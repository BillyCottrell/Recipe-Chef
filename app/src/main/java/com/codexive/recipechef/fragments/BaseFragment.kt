package com.codexive.recipechef.fragments

import android.support.v4.app.Fragment

open class BaseFragment : Fragment(){
    open var TAG: String = ""

    companion object {
        const val LIST = 0
        const val MYRECIPES = 1
        const val LEFTOVERS = 2
    }
}
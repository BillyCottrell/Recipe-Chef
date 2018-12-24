package com.codexive.recipechef.fragments

import android.support.v4.app.Fragment

open class BaseFragment : Fragment(){
    open var TAG: String = ""

    companion object {
        const val POPULAR = 0
        const val LIST = 1
        const val LEFTOVERS = 2
        const val ACCOUNT = 3
    }
}
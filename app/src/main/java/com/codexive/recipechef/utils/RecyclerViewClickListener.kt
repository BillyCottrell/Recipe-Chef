package com.codexive.recipechef.utils

import com.codexive.recipechef.model.Recipe

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(recipe: Recipe)
}
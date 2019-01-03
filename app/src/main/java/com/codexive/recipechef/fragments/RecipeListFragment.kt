package com.codexive.recipechef.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.codexive.recipechef.R
import com.codexive.recipechef.adapter.RecipeAdapter
import com.codexive.recipechef.model.Ingredient
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.utils.RecyclerViewClickListener

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RecipeListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RecipeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RecipeListFragment : Fragment(), RecyclerViewClickListener{

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var recipeArrayList: ArrayList<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        // get view from inside the fragment
        recyclerView = view.findViewById<RecyclerView>(R.id.recList)
        // put its layout on Linearlayout and give the context with it
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        // make an empty arraylist of recipes
        recipeArrayList = arrayListOf()
        // make a new recipe_list_item adapter with the context, arraylist and itemlistener
        adapter = RecipeAdapter(this.requireContext(), recipeArrayList, this)
        // set the recyclerView its adapter
        recyclerView.adapter = adapter
        // create the recipes
        createListData()

        return view
    }

    private fun createListData() {
        recipeArrayList.add(
            Recipe(
                "Bourbon and Brown Sugar Flank Steak",
                false,
                "Marinate budget-friendly flank steak in a sweet and tangy bourbon sauce, then grill until done.  Serve with the homemade garlic-chive mashed potatoes and a mixed green salad.",
                6,
                "Fishbook",
                arrayOf(
                    Ingredient(
                        "packed dark brown sugar",
                        "Steak and marinade",
                        "1/4",
                        "cup"
                    ),
                    Ingredient(
                        "Minced green onions",
                        "Steak and marinade",
                        "1/4",
                        "cup"
                    ),
                    Ingredient(
                        "Bourbon",
                        "Steak and marinade",
                        "1/4",
                        "cup"
                    ),
                    Ingredient(
                        "low-sodium soy sauce",
                        "Steak and marinade",
                        "1/4",
                        "cup"
                    ),
                    Ingredient(
                        "Dijon mustard",
                        "Steak and marinade",
                        "1/4",
                        "cup"
                    ),
                    Ingredient(
                        "Freshly ground pepper",
                        "Steak and marinade",
                        "1/2",
                        "teaspoon"
                    ),
                    Ingredient(
                        "Worcestershire sauce",
                        "Steak and marinade",
                        "1/4",
                        "teaspoon"
                    ),
                    Ingredient(
                        "Flank Steak",
                        "Steak and marinade",
                        "2",
                        "pounds",
                        "trimmed"
                    ),
                    Ingredient(
                        "Non-Stick cooking spray",
                        "Steak and marinade"
                    ),
                    Ingredient(
                        "Cornstarch",
                        "Steak and marinade",
                        "1/2",
                        "teaspoon"
                    ),
                    Ingredient(
                        "small red potatoes",
                        "Potatoes",
                        "3",
                        "pounds"
                    ),
                    Ingredient(
                        "garlic cloves",
                        "Potatoes",
                        "6",
                        notes = "peeled"
                    ),
                    Ingredient(
                        "reduced-fat sour cream",
                        "Potatoes",
                        "1/2",
                        "cup"
                    ),
                    Ingredient(
                        "2% reduced-fat milk",
                        "Potatoes",
                        "1/3",
                        "cup"
                    ),
                    Ingredient(
                        "butter",
                        "Potatoes",
                        "2 1/2",
                        "tablespoons"
                    ),
                    Ingredient(
                        "salt",
                        "Potatoes",
                        "1",
                        "teaspoon"
                    ),
                    Ingredient(
                        "freshly ground pepper",
                        "Potatoes",
                        "1/4",
                        "teaspoon"
                    ),
                    Ingredient(
                        "chopped fresh chives",
                        "Potatoes",
                        "1/4",
                        "cup"
                    ),
                    Ingredient(
                        "Fresh chives",
                        "Potatoes",
                        "1",
                        "bunch",
                        "Garnish, cut into 1 inch pieces"
                    )
                ),
                510,
                arrayOf(
                    "To prepare steak, combine first 7 ingredients in a large zip-top plastic bag; add steak. Seal and marinate in refrigerator 8 hours or overnight, turning bag occasionally. Remove steak from bag, reserving marinade.",
                    "Prepare grill.",
                    "Place steak on grill rack coated with cooking spray; grill 5 minutes on each side or until desired degree of doneness. Let stand 10 minutes. Cut diagonally across grain into thin slices.",
                    "Combine reserved marinade and cornstarch in a saucepan. Bring to a boil; cook 1 minute, stirring constantly.",
                    "To prepare potatoes, place potatoes and garlic in a large Dutch oven; cover with water. Bring to a boil. Reduce heat; simmer 30 minutes or until tender. Drain.",
                    "Return potatoes and garlic to pan, and place over medium heat. Add sour cream, milk, butter, salt, and 1/4 teaspoon pepper. Mash potato mixture to desired consistency with a potato masher. Stir in chopped chives. Mound 3/4 cup potatoes on each of 8 plates; arrange 3 ounces steak around each serving of potatoes. Drizzle 1 tablespoon sauce on each plate; sprinkle with chive pieces, if desired."
                ),
                ""
            )
        )
        // if all recipes are made then notify the adapter that the data has changed
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun recyclerViewListClicked(recipe: Recipe) {
        listener?.listClicked(recipe)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun listClicked(recipe: Recipe)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RecipeListFragment.
         */
        @JvmStatic
        fun newInstance() =
            RecipeListFragment()
    }
}

package com.codexive.recipechef.fragments

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.codexive.recipechef.R
import com.codexive.recipechef.adapter.RecipeAdapter
import com.codexive.recipechef.databinding.FragmentRecipeListBinding
import com.codexive.recipechef.model.Ingredient
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.ui.RecipeViewModel
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
class RecipeListFragment : Fragment(), RecyclerViewClickListener {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var recipeArrayList: ArrayList<Recipe>
    private lateinit var viewModel: RecipeViewModel
    private lateinit var binding: FragmentRecipeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(RecipeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false)
        //val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        val rootView = binding.root
        binding.recViewModel = viewModel
        binding.setLifecycleOwner(activity)
        // get view from inside the fragment
        recyclerView = rootView.findViewById(R.id.recList)
        // put its layout on Linearlayout and give the context with it
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.setItemViewCacheSize(4)
        // make an empty arraylist of recipes
        recipeArrayList = arrayListOf()
        // make a new recipe_list_item adapter with the context, arraylist and itemlistener
        adapter = RecipeAdapter(this.requireContext(), recipeArrayList, this)
        // set the recyclerView its adapter
        recyclerView.adapter = adapter
        // create the recipes
        getAllRecipes()
        adapter.notifyDataSetChanged()

        return rootView
    }

    override fun onStart() {
        super.onStart()
        isNetworkAvailable(requireContext())
    }

    private fun getAllRecipes() {
        val changeObserver = Observer<ArrayList<Recipe>> { value ->
            value?.let {
                recipeArrayList = it
                adapter.setRecipes(recipeArrayList)
            }
        }
        viewModel.recipeList.observe(this, changeObserver)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cm.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
        AlertDialog.Builder(this.requireContext()).setTitle("Geen internet verbinding")
            .setMessage("Hierdoor kunnen niet alle gegevens getoond worden.\nIndien u dit niet wenst gelieve dan uw internet verbinding aan te zetten")
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
        return false
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
        listener?.recipeClicked(recipe)
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
        fun recipeClicked(recipe: Recipe)
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

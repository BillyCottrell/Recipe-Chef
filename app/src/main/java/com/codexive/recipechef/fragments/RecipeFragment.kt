package com.codexive.recipechef.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codexive.recipechef.R
import com.codexive.recipechef.model.Recipe
import kotlinx.android.synthetic.main.fragment_recipe.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RecipeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RecipeFragment : BaseFragment() {

    private var recipe: Recipe? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipe = it.getSerializable("recipe") as Recipe
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.recipeClicked()
    }

    override fun onStart() {
        super.onStart()
        recipeImage.setImageResource(recipeImage.resources.getIdentifier("keldermanlunch1", "drawable", "com.codexive.recipechef"))
        txtNaam.text = recipe?.naam
        txtPreparationTime.text = String.format("%duur %dmin",recipe!!.bereidingstijd / 60, recipe!!.bereidingstijd % 60)
        txtBeschrijving.text = recipe?.beschrijving
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        TAG = "RecipeFragment"
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
        fun recipeClicked()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment PopularFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(recipe: Recipe) =
            RecipeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("recipe", recipe)
                }
            }
    }
}
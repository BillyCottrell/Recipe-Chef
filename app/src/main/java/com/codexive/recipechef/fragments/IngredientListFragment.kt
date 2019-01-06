package com.codexive.recipechef.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.codexive.recipechef.R
import com.codexive.recipechef.adapter.IngredientListAdapter
import com.codexive.recipechef.model.Ingredient
import com.codexive.recipechef.model.Recipe

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IngredientListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IngredientListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class IngredientListFragment : Fragment() {
    //private var listener: OnFragmentInteractionListener? = null

    private var ingredientListView : ExpandableListView? = null

    private var ingredientListAdapter : IngredientListAdapter? = null

    private var listDataGroup : ArrayList<String>? = arrayListOf()

    private var listDataChild : HashMap<String, ArrayList<Ingredient>>? = hashMapOf()

    private var recipe : Recipe? = null

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
        val view = inflater.inflate(R.layout.fragment_ingredient_list, container, false)
        ingredientListView = view.findViewById(R.id.ingredientList)
        listDataGroup = arrayListOf()
        listDataChild = hashMapOf()
        ingredientListAdapter = IngredientListAdapter(this.requireContext(), listDataGroup!!, listDataChild!!)
        ingredientListView!!.setAdapter(ingredientListAdapter)
        initListData()
        return view
    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }*/

    /*override fun onDetach() {
        super.onDetach()
        listener = null
    }*/

    private fun initListData() {
        val ingredients : List<Ingredient> = recipe!!.ingredients
        for (it in ingredients) {
            if(!(listDataGroup!!.contains(it.category))){
                listDataGroup!!.add(it.category)
            }
            if(listDataChild!!.containsKey(it.category)){
                var inglist = listDataChild!![it.category]
                inglist!!.add(it)
            } else {
                var inglist = arrayListOf<Ingredient>()
                inglist!!.add(it)
                listDataChild!![it.category] = inglist
            }
        }
        ingredientListAdapter!!.notifyDataSetChanged()
        for(i in 0 until listDataChild!!.size){
            ingredientListView!!.expandGroup(i)
        }
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
    /*interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IngredientListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(recipe : Recipe) =
            IngredientListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("recipe", recipe)
                }
            }
    }
}

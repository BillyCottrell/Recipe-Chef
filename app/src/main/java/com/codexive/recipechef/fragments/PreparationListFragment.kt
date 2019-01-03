package com.codexive.recipechef.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView

import com.codexive.recipechef.R
import com.codexive.recipechef.adapter.PreparationListAdapter
import com.codexive.recipechef.model.Recipe

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PreparationListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PreparationListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PreparationListFragment : Fragment() {
    //private var listener: OnFragmentInteractionListener? = null

    private var preparationListView : ExpandableListView? = null

    private var preparationListAdapter : PreparationListAdapter? = null

    private var listDataGroup : ArrayList<String>? = arrayListOf()

    private var listDataChild : HashMap<String, String>? = hashMapOf()

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
        val view = inflater.inflate(R.layout.fragment_preparation_list, container, false)
        preparationListView = view.findViewById(R.id.preparationList)
        listDataGroup = arrayListOf()
        listDataChild = hashMapOf()
        preparationListAdapter = PreparationListAdapter(this.requireContext(), listDataGroup!!, listDataChild!!)
        preparationListView!!.setAdapter(preparationListAdapter)
        initListData()
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

   /* override fun onAttach(context: Context) {
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

    private fun initListData(){
        val preparationMethod : Array<String> = recipe!!.preparationMethod
        preparationMethod.forEach {
            val step = String.format("Step %s",listDataGroup!!.size+1)
            listDataGroup!!.add(step)
            listDataChild!![step]=it
        }
        for(i in 0 until listDataChild!!.size){
            preparationListView!!.expandGroup(i)
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
         * @return A new instance of fragment PreparationListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(recipe : Recipe) =
            PreparationListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("recipe", recipe)
                }
            }
    }
}

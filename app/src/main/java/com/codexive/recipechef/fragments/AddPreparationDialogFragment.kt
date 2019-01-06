package com.codexive.recipechef.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.codexive.recipechef.R
import com.codexive.recipechef.model.Ingredient
import com.codexive.recipechef.utils.DialogDataSender
import kotlinx.android.synthetic.main.fragment_add_preparation_dialog.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddPreparationDialogFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddPreparationDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddPreparationDialogFragment : DialogFragment() {
    //private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_preparation_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        val dataSender = targetFragment as DialogDataSender
        btnAddPreparationMethod.setOnClickListener {
            dataSender.passPreparationMethod(inputPreparationMethod.text.toString())
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

    /*override fun onAttach(context: Context) {
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
         * @return A new instance of fragment AddPreparationDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AddPreparationDialogFragment()
    }
}

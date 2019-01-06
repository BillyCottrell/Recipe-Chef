package com.codexive.recipechef.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView

import com.codexive.recipechef.R
import com.codexive.recipechef.adapter.IngredientListAdapter
import com.codexive.recipechef.adapter.PreparationListAdapter
import com.codexive.recipechef.model.Ingredient
import com.codexive.recipechef.model.Recipe
import com.codexive.recipechef.utils.DialogDataSender
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe.*
import android.opengl.ETC1.getWidth
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.ExpandableListAdapter
import com.codexive.recipechef.ui.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_ingredient_list.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddRecipeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddRecipeFragment : Fragment(), DialogDataSender {
    private var listener: OnFragmentInteractionListener? = null
    private var ingredientListView : ExpandableListView? = null
    private var ingredientListAdapter : IngredientListAdapter? = null
    private var ingredientListDataGroup : ArrayList<String>? = arrayListOf()
    private var ingredientListDataChild : HashMap<String, ArrayList<Ingredient>>? = hashMapOf()
    private var preparationListView : ExpandableListView? = null
    private var preparationListAdapter : PreparationListAdapter? = null
    private var preparationListDataGroup : ArrayList<String>? = arrayListOf()
    private var preparationListDataChild : HashMap<String, String>? = hashMapOf()
    private var ingredients : ArrayList<Ingredient> = arrayListOf()
    private var preparationMethod : ArrayList<String> = arrayListOf()
    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(RecipeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_recipe, container, false)

        ingredientListView = view.findViewById(R.id.ingredientList)
        ingredientListDataGroup = arrayListOf()
        ingredientListDataChild = hashMapOf()
        ingredientListAdapter = IngredientListAdapter(this.requireContext(), ingredientListDataGroup!!, ingredientListDataChild!!)
        ingredientListView!!.setAdapter(ingredientListAdapter)
        ingredientListView!!.setOnGroupClickListener { parent, v, groupPosition, id ->
            setListViewHeight(parent, groupPosition)
            false
        }
        initIngredientListData()

        preparationListView = view.findViewById(R.id.preparationList)
        preparationListDataGroup = arrayListOf()
        preparationListDataChild = hashMapOf()
        preparationListAdapter = PreparationListAdapter(this.requireContext(), preparationListDataGroup!!, preparationListDataChild!!)
        preparationListView!!.setAdapter(preparationListAdapter)
        preparationListView!!.setOnGroupClickListener { parent, v, groupPosition, id ->
            setListViewHeight(parent, groupPosition)
            false
        }
        initPreparationListData()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        preparationTime.setIs24HourView(true)
        btnImageUpload.setOnClickListener{
            if(inputRecipeImage.text.toString()!=""){
                recipeImageUpload.visibility = View.VISIBLE
                Picasso.get().load(inputRecipeImage.text.toString()).into(recipeImageUpload)
            }
        }
        addIngredient.setOnClickListener{
            val addIngredientDialogFragment = AddIngredientDialogFragment()
            addIngredientDialogFragment.setTargetFragment(this, 0)
            addIngredientDialogFragment.show(this.requireFragmentManager(), "addIngredientDialog")
        }
        addPreparation.setOnClickListener{
            val addPreparationDialogFragment = AddPreparationDialogFragment()
            addPreparationDialogFragment.setTargetFragment(this, 0)
            addPreparationDialogFragment.show(this.requireFragmentManager(), "addPreparationDialog")
        }
        addRecipe.setOnClickListener {
            var errors = false
            if(inputRecipeName.text.toString()==""){
                errors = true
                layoutRecipeName.error = getString(R.string.empty_name_error)
            } else{
                layoutRecipeName.error = null
            }
            if(inputRecipeDescription.text.toString()==""){
                errors = true
                layoutRecipeDescription.error = getString(R.string.empty_description_error)
            } else {
                layoutRecipeDescription.error = null
            }
            if(inputRecipeServings.text.toString()==""){
                errors = true
                layoutRecipeServings.error = getString(R.string.empty_servings_error)
            } else {
                layoutRecipeServings.error = null
            }
            if(inputRecipeImage.text.toString()==""){
                errors = true
                layoutRecipeImage.error = getString(R.string.empty_image_error)
            } else {
                layoutRecipeImage.error = null
            }
            if(ingredients.isEmpty()){
                errors = true
                errorIngredients.visibility = View.VISIBLE
            } else{
                errorIngredients.visibility = View.GONE
            }
            if(preparationMethod.isEmpty()){
                errorPreparationMethod.visibility = View.VISIBLE
                errors = true
            } else{
                errorPreparationMethod.visibility = View.GONE
            }
            if(preparationTime.hour==0&&preparationTime.minute<5){
                errorPreparationTime.visibility = View.VISIBLE
                errors = true
            } else{
                errorPreparationTime.visibility = View.GONE
            }
            if(!errors){
                val prepTime = preparationTime.hour*60 + preparationTime.minute
                val recipe = Recipe(
                    inputRecipeName.text.toString(),
                    inputRecipeDescription.text.toString(),
                    inputRecipeServings.text.toString().toInt(),
                    ingredients,
                    prepTime,
                    preparationMethod,
                    inputRecipeImage.text.toString()
                )
                viewModel.addRecipe(recipe)
                listener?.createRecipeClicked()
            }
        }
    }

    private fun initIngredientListData() {
        val ingredients : ArrayList<Ingredient> = ingredients
        ingredients.forEach {
            if(!(ingredientListDataGroup!!.contains(it.category))){
                ingredientListDataGroup!!.add(it.category)
            }
            if(ingredientListDataChild!!.containsKey(it.category)){
                val inglist = ingredientListDataChild!![it.category]
                inglist!!.add(it)
            } else {
                val inglist = arrayListOf<Ingredient>()
                inglist.add(it)
                ingredientListDataChild!![it.category] = inglist
            }
        }
        ingredientListAdapter!!.notifyDataSetChanged()
    }

    private fun initPreparationListData(){
        val preparationMethod : ArrayList<String> = preparationMethod
        preparationMethod.forEach {
            val step = String.format("Step %s",preparationListDataGroup!!.size+1)
            preparationListDataGroup!!.add(step)
            preparationListDataChild!![step]=it
        }
        preparationListAdapter!!.notifyDataSetChanged()
    }

    override fun passIngredient(ing: Ingredient) {
        ingredients.add(ing)
        Log.d("ingredient",String.format(ing.ingredientName))
        initIngredientListData()
    }

    override fun passPreparationMethod(prep: String) {
        preparationMethod.add(prep)
        initPreparationListData()
    }

    private fun setListViewHeight(expandableListView: ExpandableListView, group : Int){
        val listAdapter = expandableListView.expandableListAdapter as ExpandableListAdapter
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(
            expandableListView.width,
            View.MeasureSpec.EXACTLY
        )
        for (i in 0 until listAdapter.groupCount) {
            val groupItem = listAdapter.getGroupView(i, false, null, expandableListView)
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)

            totalHeight += groupItem.measuredHeight

            if (expandableListView.isGroupExpanded(i) && i != group || !expandableListView.isGroupExpanded(i) && i == group) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val listItem = listAdapter.getChildView(
                        i, j, false, null,
                        expandableListView
                    )
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)

                    totalHeight += listItem.measuredHeight

                }
            }
        }
        val params = expandableListView.layoutParams
        var height = totalHeight + expandableListView.dividerHeight * (listAdapter.groupCount - 1)
        if (height < 10)
            height = 200
        params.height = height
        expandableListView.layoutParams = params
        expandableListView.requestLayout()
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
        fun createRecipeClicked()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AddRecipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AddRecipeFragment()
    }
}

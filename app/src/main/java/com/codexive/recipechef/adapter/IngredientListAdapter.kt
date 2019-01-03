package com.codexive.recipechef.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.codexive.recipechef.R
import com.codexive.recipechef.model.Ingredient

class IngredientListAdapter(
    private var context: Context,
    private var listDataGroup : ArrayList<String>,
    private var listDataChild : HashMap<String, ArrayList<Ingredient>>) :
    BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any {
        return this.listDataGroup[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val headerTitle = String.format("%s:",getGroup(groupPosition))
        val view = LayoutInflater.from(context).inflate(R.layout.ingredient_list_group, parent, false)
        val txtCategory = view.findViewById<TextView>(R.id.txtCategory)
        txtCategory.text = headerTitle
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.listDataChild[this.listDataGroup[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.listDataChild[this.listDataGroup[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val ingredient = getChild(groupPosition, childPosition) as Ingredient
        val view = LayoutInflater.from(context).inflate(R.layout.ingredient_list_item, parent, false)
        val txtQuantity = view.findViewById<TextView>(R.id.txtQuantity)
        val txtMeasurement = view.findViewById<TextView>(R.id.txtMeasurement)
        val txtIngredientName = view.findViewById<TextView>(R.id.txtIngredientName)
        val txtNotes = view.findViewById<TextView>(R.id.txtNotes)
        if(ingredient.quantity==""){
            txtQuantity.visibility = View.GONE
        } else{
            txtQuantity.text=ingredient.quantity
        }
        if(ingredient.measurement==""){
            txtMeasurement.visibility = View.GONE
        } else{
            txtMeasurement.text = ingredient.measurement
        }
        if(ingredient.notes==""){
            txtNotes.visibility = View.GONE
        } else{
            txtNotes.text = ingredient.notes
        }
        txtIngredientName.text = ingredient.ingredientName
        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return this.listDataGroup.size
    }
}
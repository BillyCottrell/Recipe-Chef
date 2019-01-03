package com.codexive.recipechef.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.codexive.recipechef.R

class PreparationListAdapter(
    private var context: Context,
    private var listDataGroup : ArrayList<String>,
    private var listDataChild : HashMap<String, String>) :
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
        val view = LayoutInflater.from(context).inflate(R.layout.preparation_list_group, parent, false)
        val txtStep = view.findViewById<TextView>(R.id.txtStep)
        txtStep.text = listDataGroup[groupPosition]
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.listDataChild[this.listDataGroup[groupPosition]]!!
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
        val preparation = getChild(groupPosition, childPosition) as String
        val view = LayoutInflater.from(context).inflate(R.layout.preparation_list_item, parent, false)
        val txtPreparation = view.findViewById<TextView>(R.id.txtPreparation)
        txtPreparation.text = preparation
        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return this.listDataGroup.size
    }
}
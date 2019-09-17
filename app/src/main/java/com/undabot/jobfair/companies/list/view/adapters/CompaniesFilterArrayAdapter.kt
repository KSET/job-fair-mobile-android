package com.undabot.jobfair.companies.list.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.list.view.TopLineTextView
import com.undabot.jobfair.companies.view.models.IndustryViewModel

class CompaniesFilterArrayAdapter(context: Context) : ArrayAdapter<IndustryViewModel>(
    context,
    R.layout.company_filter_item
) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = super.getView(position, convertView, parent)
        view.setLineThickness()
        return view
    }

    private fun View.setLineThickness() {
        if (this is TopLineTextView) {
            setLineThickness(R.dimen.outline_thickness)
        }
    }
}
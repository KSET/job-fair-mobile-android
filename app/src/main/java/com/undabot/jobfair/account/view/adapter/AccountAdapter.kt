package com.undabot.jobfair.account.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.undabot.jobfair.R
import com.undabot.jobfair.account.view.model.AccountViewModel
import kotlinx.android.synthetic.main.account_list_item_company.view.*
import kotlinx.android.synthetic.main.account_list_item_student.view.*

class AccountAdapter(
    val items: List<AccountViewModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is AccountViewModel.Company -> R.layout.account_list_item_company
            is AccountViewModel.Student -> R.layout.account_list_item_student
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, layoutResId: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return when (layoutResId) {
            R.layout.account_list_item_company -> CompanyViewHolder(view)
            R.layout.account_list_item_student -> StudentViewHolder(view)
            else -> throw NotImplementedError("Unsupported layout res id")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CompanyViewHolder -> holder.bind(items[position] as AccountViewModel.Company)
            is StudentViewHolder -> holder.bind(items[position] as AccountViewModel.Student)
        }
    }

    override fun getItemCount(): Int = items.count()

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(company: AccountViewModel.Company) {
            itemView.companyName.text = company.name
        }
    }

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(student: AccountViewModel.Student) {
            itemView.studentName.text = student.name
            itemView.barcodeImage.setImageBitmap(student.qrCode)
        }
    }
}
package com.undabot.jobfair.support.drinks.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.undabot.jobfair.R
import com.undabot.jobfair.support.drinks.view.DrinkViewModel
import kotlinx.android.synthetic.main.drink_layout.view.*

class DrinksAdapter(
    val viewModels: List<DrinkViewModel>
) : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        return DrinksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.drink_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        holder.bind(viewModels[position])
    }

    override fun getItemCount(): Int = viewModels.count()

    class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DrinkViewModel) {
            val context = itemView.context
            itemView.name.text = context.getString(item.name)
            itemView.image.setImageDrawable(ContextCompat.getDrawable(context, item.image))
            updateCount(item)
            itemView.decrease.setOnClickListener { decreaseCount(item) }
            itemView.increase.setOnClickListener { increaseCount(item) }
        }

        private fun updateCount(item: DrinkViewModel) {
            itemView.count.text = item.count.toString()
            itemView.decrease.isEnabled = item.count > MIN_DRINKS
            itemView.increase.isEnabled = item.count < MAX_DRINKS
        }

        private fun decreaseCount(item: DrinkViewModel) {
            item.count--
            updateCount(item)
        }

        private fun increaseCount(item: DrinkViewModel) {
            item.count++
            updateCount(item)
        }
    }

    companion object {
        private const val MIN_DRINKS = 0
        private const val MAX_DRINKS = 10
    }
}
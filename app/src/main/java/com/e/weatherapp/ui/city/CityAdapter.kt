package com.e.weatherapp.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.weatherapp.R
import com.e.weatherapp.model.City

class CityAdapter(private val list: MutableList<City>) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_view_city, parent, false)
    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: City) {
            //
        }
    }
}



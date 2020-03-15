package com.e.weatherapp.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.weatherapp.R
import com.e.weatherapp.model.city.CityDataModel

class CityAdapter(private val function: (CityDataModel) -> Unit) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private var cityList: MutableList<CityDataModel>? = null

    fun updateList(list: MutableList<CityDataModel>) {
        cityList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view: View = LayoutInflater.from(parent.context!!)
            .inflate(R.layout.item_view_city, parent, false)
        return CityViewHolder(view, function)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cityList!![position])
    }

    override fun getItemCount(): Int {
        if (cityList != null) {
            return cityList!!.size
        }
        return 0
    }

    inner class CityViewHolder(itemView: View, val function: (CityDataModel) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val cityTitle: TextView = itemView.findViewById(R.id.tv_city)
        val citySubTitle: TextView = itemView.findViewById(R.id.tv_country)

        fun bind(city: CityDataModel) {
            cityTitle.text = city.name
            citySubTitle.text = city.capital

            itemView.setOnClickListener {
                function(city)
            }
        }
    }
}



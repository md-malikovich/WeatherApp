package com.e.weatherapp.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.e.weatherapp.R
import com.e.weatherapp.model.City
import com.e.weatherapp.model.city.CityDataModel
import com.e.weatherapp.repositories.CitiesRepository

class CityAdapter(private val list: MutableList<City>) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    private var cityList: ArrayList<City>? = null

    fun setList(list: ArrayList<City>) {
        cityList = list
        notifyDataSetChanged()
    }

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

        var cityData = MutableLiveData<List<CityDataModel>>()
        fun bind(city: City) {
            //
            city.name

        }
    }
}



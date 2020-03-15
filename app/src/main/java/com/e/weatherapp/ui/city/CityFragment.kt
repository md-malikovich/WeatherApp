package com.e.weatherapp.ui.city

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.weatherapp.MainActivity
import com.e.weatherapp.R
import com.e.weatherapp.base.BaseFragment
import com.e.weatherapp.model.city.CityDataModel
import com.e.weatherapp.ui.detail_city.DetailCityActivity
import com.e.weatherapp.utils.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFragment : BaseFragment (R.layout.fragment_city) {

    private lateinit var searchView: SearchView
    private val viewModel: CityViewModel by viewModel()
    private lateinit var cityAdapter: CityAdapter
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): Fragment {
            return CityFragment()
        }
    }

    override fun initViews(view: View) {
        searchView = view.findViewById(R.id.searchView)
        recyclerView = view.findViewById(R.id.recycler_view_city)
        initRecyclerView()
        getCityData()
    }

    override fun loadingStatus() {
        viewModel.loading.observe(this@CityFragment, Observer {
            Log.v("viewmodel state: ", viewModel.loading.value.toString())
        })
    }

    private fun initRecyclerView() {
        cityAdapter = CityAdapter(this@CityFragment::onClickItem)
        recyclerView.apply {
            adapter = cityAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun onClickItem(city: CityDataModel) {
        val intent = Intent(context, DetailCityActivity::class.java)
        intent.putExtra("city", city.flag)
        intent.putExtra("city", city.name)
        startActivity(intent)
        Toast.message(context!!, "" + city.name)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View? = inflater.inflate(R.layout.fragment_city, container, false)
        view?.let { initViews(it) }
        return view
    }

    private fun getCityData() {
        searchView.queryHint = "Введите название города"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                val timer = object : CountDownTimer(800, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        viewModel.loading.value = true
                        viewModel.getCityData(newText)
                        viewModel.cities.observe(viewLifecycleOwner, Observer {
                            if (!it.isNullOrEmpty())
                                cityAdapter.updateList(it as MutableList<CityDataModel>)
                        })
                    }
                }
                timer.start()
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }
}
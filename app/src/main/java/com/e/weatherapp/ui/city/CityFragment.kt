package com.e.weatherapp.ui.city

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.weatherapp.MainActivity
import com.e.weatherapp.R
import com.e.weatherapp.model.City
import com.e.weatherapp.utils.Toast
import kotlinx.android.synthetic.main.fragment_city.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFragment : Fragment() {

    private lateinit var searchView: SearchView
    private val viewModel: CityViewModel by viewModel()
    private lateinit var cityAdapter: CityAdapter

    companion object {
        fun newInstance(): Fragment {
            return CityFragment()
        }
    }

    private fun bindView(view: View) {
        searchView = view.findViewById(R.id.searchView)
        initRecyclerView()
        //getCityData()
    }

    private fun initRecyclerView() {
//        recycler_view_countries.apply {
//            cityAdapter = CityAdapter(this@CityFragment::onClickItem)
//            adapter = cityAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
    }

    private fun onClickItem(city: City) {
        val intent = Intent(context, MainActivity::class.java)
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
        view?.let { bindView(it) }
        searchCity()
        return view
    }

    private fun searchCity() {
        //
    }
}

//    private fun getCityData() {
//        search_view.queryHint = "Введите название города"
//        search_view.setOnQueryTextListener(object :
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String): Boolean {
//
//                val timer = object : CountDownTimer(2000, 1000) {
//
//                    override fun onTick(millisUntilFinished: Long) {}
//                    override fun onFinish() {
//                        searchViewModel.getCity(newText)
//                        searchViewModel.cityData.observe(this@CityFragment,
//                            Observer { data ->
//                                if (data != null) {
//                                    searchAdapter.setList(data as ArrayList<CityModel>)
//                                }
//                            })
//                      }
//                }
//                return true
//            }
//
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//        })
//    }


//        editTextSearch.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(s: Editable) {}
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence, start: Int,
//                                       before: Int, count: Int) {
//                //viewModel.getCityData(searchView.text.toString())
//            }
//        })
//    }
package com.e.weatherapp.ui.map

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.e.weatherapp.R
import com.e.weatherapp.model.Coord
import com.e.weatherapp.ui.WeatherFragment
import com.e.weatherapp.utils.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener, GoogleMap.OnMapClickListener,
    GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel()
    private lateinit var fab: FloatingActionButton

    companion object {
        fun newInstance() : Fragment {
            return MapFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View? = inflater.inflate(R.layout.fragment_map, container, false)
        setupMap()
        view?.let { bindView(it) } //TODO:???
        return view
    }

    private fun bindView(view: View) { //TODO:???
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            com.e.weatherapp.utils.Toast.message(context!!, "On Fab click!")
            //showFragment()
        }
    }

    private fun showFragment() { //TODO:???
       activity?.supportFragmentManager?.beginTransaction()?.add(R.id.map_container, WeatherFragment())?.commit()
    }

    private fun getWeather(latLng: LatLng) {
        viewModel.getWeatherData("metric", latLng.latitude, latLng.longitude).observe(activity!!, Observer {
            tv_cityName?.text = it.name
            tv_temp?.text = it.main.temp.toInt().toString() //"$ {it!!.main.temp.toInt()}"
            tv_yasno?.text = it.weather[0].main
            tv_humidity?.text = it.main.humidity.toString() //"$ {it!!.main.humidity}"
            //TODO: gradle
        })
    }

    private fun setupMap() {
        val mapFragment = SupportMapFragment.newInstance()
        fragmentManager?.beginTransaction()?.add(R.id.map_container, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setOnMapClickListener {
            com.e.weatherapp.utils.Toast.message(context!!, "On map click!")
            val weatherFragment = WeatherFragment()
            val fm = activity?.supportFragmentManager
            if (fm?.findFragmentByTag("Weather") != null) {
                fm.beginTransaction().show(fm.findFragmentByTag("Weather")!!).commit()
            } else {
                fm!!.beginTransaction().add(R.id.main_fragment, weatherFragment, "Weather")
                    .commit()
            }
            getWeather(it)
        }
    }

    override fun onCameraMove() {
        //
    }

    override fun onCameraIdle() {
        //
    }

    @SuppressLint("toast")
    override fun onMapClick(p0: LatLng?) {
        //Toast.makeText(activity, p0?.latitude.toString(), Toast.LENGTH_LONG).show()
    }
}

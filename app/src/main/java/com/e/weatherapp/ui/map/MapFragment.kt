package com.e.weatherapp.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.weatherapp.R
import com.e.weatherapp.ui.WeatherFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel()
    private lateinit var fab: FloatingActionButton
    //private lateinit var mapFragment: SupportMapFragment //TODO:???

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
        getWeather()
        view?.let { bindView(it) } //TODO:???
        return view
    }

    private fun bindView(view: View) { //TODO:???
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            showFragment()
            Log.d("ololo", "fab")
        }
    }

    private fun showFragment() { //TODO:???
       activity?.supportFragmentManager?.beginTransaction()?.add(R.id.map_container, WeatherFragment())?.commit()
    }

    private fun getWeather() {
        viewModel.getWeatherData("metrics", "35", "139")
    }

    private fun setupMap() {
        val mapFragment = SupportMapFragment.newInstance()
        fragmentManager?.beginTransaction()?.add(R.id.map_container, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        showFragment()
//        mMap.setOnMapClickListener {
//            mapFragment = SupportMapFragment.newInstance()
//            fragmentManager?.beginTransaction()?.add(R.id.weather_fragment, WeatherFragment())?.commit()
//            mapFragment.getMapAsync(this)
//        }
    }

    override fun onCameraMove() {
        //
    }

    override fun onCameraIdle() {
        //
    }
}

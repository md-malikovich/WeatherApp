package com.e.weatherapp.ui.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.weatherapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.e.weatherapp.base.BaseFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment(R.layout.fragment_map), OnMapReadyCallback,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel()
    private lateinit var weatherBottomSheet: BottomSheetBehavior<View>

    companion object {
        fun newInstance(): Fragment {
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
        return view
    }

    override fun initViews(view: View) {
        //
    }

    override fun loadingStatus() {
        //
    }

    private fun initBottomSheet(it: Marker) {
        val bottomWeather = WeatherBottomSheet()
        if (!bottomWeather.isAdded) bottomWeather.show(activity!!.supportFragmentManager, bottomWeather.tag)
    }

    @SuppressLint("SetTextI18n")
    public fun getWeather(latLng: LatLng) {
        viewModel.getWeatherData("metric", latLng.latitude, latLng.longitude)
            .observe(activity!!, Observer {
                tv_cityName?.text = it.name
                tv_temp?.text = "${it!!.main.temp.toInt()}°"
                tv_yasno?.text = it.weather[0].main
                tv_humidity?.text = "${it.main.humidity}% влажности"
                Glide.with(context!!)
                    .load("http://openweathermap.org/img/wn/${it.weather[0].icon}.png")
                    .into(img_sun)
            })
    }

    private fun setupMap() {
        val mapFragment = SupportMapFragment.newInstance()
        fragmentManager?.beginTransaction()?.add(R.id.map_container, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
    }

    private fun addMarkerToMap(location: LatLng) {
        mMap.clear()
        val markerOptions = MarkerOptions()
            .position(location)
            .title("My marker")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
        mMap.addMarker(markerOptions).showInfoWindow()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraMoveListener (this)
        mMap.setOnCameraIdleListener (this)
        mMap.setOnMapClickListener {
            android.widget.Toast.makeText(
                    activity?.applicationContext,
                    "LAT: ${it.latitude} LNG: ${it.longitude}",
                    android.widget.Toast.LENGTH_SHORT).show()
            addMarkerToMap(it)
        }
        mMap.setOnInfoWindowClickListener {
            initBottomSheet(it)
            //displayWeather(it)
        }
    }

    private fun displayWeather(it: Marker) {
        weatherBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCameraMove() {
        //
    }

    override fun onCameraIdle() {
        //
    }
}
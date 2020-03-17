package com.e.weatherapp.ui.map

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.weatherapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.e.weatherapp.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.math.roundToInt

class MapFragment : BaseFragment(R.layout.fragment_map), OnMapReadyCallback,
    GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener{ //TODO:???

    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel()

    companion object {
        fun newInstance(): Fragment {
            return MapFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMap()
    }

    private fun setupMap() {
        val mapFragment = SupportMapFragment.newInstance()
        fragmentManager?.beginTransaction()?.add(R.id.map_container, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraMoveListener (this)
        mMap.setOnCameraIdleListener (this)
        mMap.setOnMapClickListener {
            viewModel.getWeatherData("metric",it.latitude,it.longitude)
            addMarkerToMap(it)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it,2.0f))
        }
    }

    private fun addMarkerToMap(location: LatLng) {
        mMap.clear()
        val markerOptions = MarkerOptions()
            .position(location)
            .title("Get weather")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
        val marker = mMap.addMarker(markerOptions)
        marker.showInfoWindow()
        mMap.setOnInfoWindowClickListener {
            getWeather()
        }
    }

    private fun getWeather(){
        val view: View = layoutInflater.inflate(R.layout.bottom_sheet_weather,null)
                val dialog = BottomSheetDialog(this.requireContext())
        dialog.behavior.state = BottomSheetBehavior.STATE_SETTLING  //PEEK_HEIGHT_AUTO // STATE_EXPANDED // STATE_HALF_EXPANDED
        dialog.setContentView(view, ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT))
        dialog.behavior.peekHeight = 334;
        dialog.show()

        val temp: TextView = view.findViewById(R.id.tv_temp)
        val tvYasno: TextView = view.findViewById(R.id.tv_yasno)
        val icWeather: ImageView = view.findViewById(R.id.img_sun)
        val humidity: TextView = view.findViewById(R.id.tv_humidity)
        val cityName: TextView = view.findViewById(R.id.tv_cityName)

        viewModel.liveData.observe(this, Observer {
            temp.text = it.main?.temp?.roundToInt().toString().plus("°")
            cityName.text = it.name
            tvYasno.text = it.weather[0].description
            humidity.text = it.main?.humidity.toString().plus(" % влажности")
            Glide.with(context!!)
                .load("http://openweathermap.org/img/wn/${it.weather[0].icon}.png")
                .into(icWeather)
        })
    }

    override fun initViews(view: View) {
        //
    }

    override fun loadingStatus() {
        //
    }

    override fun onCameraMove() {
        //
    }

    override fun onCameraIdle() {
        //
    }

    override fun onMapClick(p0: LatLng?) {
        //
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }
}
package com.e.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.weatherapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
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
        view?.let { setupView(it) }
        return view
    }

    private fun setupView(view: View) {
        fab = view.findViewById(R.id.fab)

        fab.setOnClickListener {
            showFragment()
        }
    }

    private fun showFragment() {
       activity?.supportFragmentManager?.beginTransaction()?.add(R.id.map_container, WeatherFragment())
           ?.commit()
    }

    private fun setupMap() {
        mapFragment = SupportMapFragment.newInstance()
        fragmentManager?.beginTransaction()?.add(R.id.map_container, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //mMap = googleMap
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

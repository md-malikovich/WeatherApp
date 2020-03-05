package com.e.weatherapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.e.weatherapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    lateinit var context: AppCompatActivity
    private lateinit var mapFragment: SupportMapFragment

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab: View = context.findViewById(R.id.fab)
        fab.setOnClickListener { view ->

           showFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as AppCompatActivity
    }

    private fun showFragment() {
        val fManager = context.supportFragmentManager
        val fragmentTransaction: FragmentTransaction
        val fragment = WeatherFragment()
        fragmentTransaction = fManager.beginTransaction()
        fragmentTransaction.add(R.id.weather_fragment, fragment) // or .replace(R.id.map_container, fragment) or fragmentTransaction.show(WeatherFragment())
            .addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun setupMap() {
        mapFragment = SupportMapFragment.newInstance()
        fragmentManager?.beginTransaction()?.add(R.id.map_container, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //
    }

    override fun onCameraMove() {
        //
    }

    override fun onCameraIdle() {
        //
    }
}

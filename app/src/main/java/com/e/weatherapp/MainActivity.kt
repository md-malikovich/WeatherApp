package com.e.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.e.weatherapp.ui.city.CityFragment
import com.e.weatherapp.ui.map.MapFragment
import com.e.weatherapp.ui.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSelectFragment(MapFragment())
        setupNavigationView()
    }

    private fun setupNavigationView() {
        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_map -> {
                    setSelectFragment(MapFragment.newInstance())
                    true
                }
                R.id.navigation_search -> {
                    setSelectFragment(CityFragment.newInstance())
                    true
                }
                R.id.navigation_profile -> {
                    setSelectFragment(WeatherFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun setSelectFragment(fr: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment, fr).addToBackStack(fr.tag).commit()
    }
}
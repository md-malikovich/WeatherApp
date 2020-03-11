package com.e.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.e.weatherapp.ui.CountriesFragment
import com.e.weatherapp.ui.MapFragment
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
                    setSelectFragment(CountriesFragment.newInstance())
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
/*
1. (Кто не сделал предыдущее задание) - сделать его!!! - 0.5б
2. Сделать отображение погоды по координатам в фрагменте погоды! - 1б
 */
package com.e.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.e.weatherapp.ui.MapFragment
import com.e.weatherapp.ui.WeatherFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSelectFragment(MapFragment())
        showWeatherFragment(WeatherFragment())

        val fab: View = findViewById(R.id.fab)
    }

    private fun setupNavigationView(fr: Fragment) {
        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_map-> {
                    setSelectFragment(MapFragment())
                    true
                }
                R.id.navigation_search -> {
                    showWeatherFragment(WeatherFragment())
                    true
                }
                R.id.navigation_profile -> {
                    //
                    true
                }
                else -> false
            }
        }
    }

    private fun setSelectFragment(fr: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_fragment, fr).commit()
    }

    private fun showWeatherFragment(fr: Fragment) {
        fab.setOnClickListener { view ->
            //supportFragmentManager.beginTransaction().show(WeatherFragment()).commit()
            supportFragmentManager.beginTransaction().show(fr).commit()
            Log.d("ololo", "show")
        }
    }
}

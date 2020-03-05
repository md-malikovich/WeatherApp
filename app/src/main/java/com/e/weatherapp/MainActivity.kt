package com.e.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.View
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
        setupNavigationView()
        setSelectFragment(MapFragment())

//        val fab: View = findViewById(R.id.fab)
//        fab.setOnClickListener { view -> //
//            MapFragment().showFragment()
//        }

//        img_close.setOnClickListener { view ->
//            //
//        }
    }

    private fun setupNavigationView() {
        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_map-> {
                    setSelectFragment(MapFragment())
                    Log.d("ololo", "1")
                    //fab.show()
                    true
                }
                R.id.navigation_search -> {
                    setSelectFragment(CountriesFragment())
                    //fab.hide()
                    Log.d("ololo", "2")
                    true
                }
                R.id.navigation_profile -> {
                    setSelectFragment(WeatherFragment())
                    //fab.hide()
                    Log.d("ololo", "3")
                    true
                }
                else -> false
            }
        }
    }

    private fun setSelectFragment(fr: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_fragment, fr).commit()
    }
}
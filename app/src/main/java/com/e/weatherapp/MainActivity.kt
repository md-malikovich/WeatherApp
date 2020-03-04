package com.e.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.e.weatherapp.ui.MapFragment
import com.e.weatherapp.ui.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSelectFragment(MapFragment())

        val btn : Button = btn_main
        btn_main.setOnClickListener {
           //
        }
    }

    private fun setupNavigationView(fr: Fragment) {
        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_map-> {
                    setSelectFragment(MapFragment())
                    true
                }
                R.id.navigation_search -> {
                    //setSelectFragment(SearchFragment())
                    true
                }
                R.id.navigation_profile -> {
                    //setSelectFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun setSelectFragment(fr: Fragment) {
//        btn_main.setOnClickListener {
//            supportFragmentManager.beginTransaction().show(R.id.weather_container, WeatherFragment).commit()
//        }
        supportFragmentManager.beginTransaction().add(R.id.main_fragment, fr).commit()
    }

//    private fun setSelectFragment(fr: Fragment? = null) {
//        fr?.let {supportFragmentManager.beginTransaction().add(R.id.main_fragment, it).commit()} // show
//    }
}

/* 1. Добавить кнопку на НАШ MapFragment(), при нажатии на кнопку нужно сделать отображение
 всплывающего окна погоды.
 2. Исправить ошибку при нажатии на мап баттон

 нажимаем на кнопку - показываем сверху новый фрагмент!!!!!!!!!!!!!!!!!!!!!!
 */
//Чтобы подключиться к API, передайте этот ключ из приложения в качестве значения параметра: key=API_KEY.
//AIzaSyBrgVTYhkl5y4yfwkxgaTeRzmm-q5o2vWo
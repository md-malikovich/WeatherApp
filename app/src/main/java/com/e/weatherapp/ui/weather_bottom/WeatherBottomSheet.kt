package com.e.weatherapp.ui.weather_bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.weatherapp.R
import com.e.weatherapp.base.BaseBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherBottomSheet : BaseBottomSheet() {

    companion object {
        fun newInstance() : Fragment {
            return WeatherBottomSheet()
        }
    }

    private var lng: Double = 0.0
    private var lat: Double = 0.0
    private val viewModel: WeatherBottomViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_weather, container, false)
        viewModel.addToFavorite(lng, lat)
        return view
    }
}




//override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//    super.onCreateView(inflater, container, savedInstanceState)
//    val view: View? = inflater.inflate(R.layout.bottom_sheet_weather, container, false)
//    return view
//}

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val bottomSheet = view.findViewById(R.id.main_btn_sheet_weather) as ConstraintLayout
//
//        val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> = BottomSheetBehavior.from(bottomSheet)
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        bottomSheetBehavior.peekHeight = 400
//        //bottomSheetBehavior.isHideable = false
//
//        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                //
//            }
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                //
//            }
//        })
//    }

package com.e.weatherapp.ui.city

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.e.weatherapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFragment : Fragment() {

    private lateinit var editTextSearch: EditText
    private val viewModel: CityViewModel by viewModel()

    companion object {
        fun newInstance() : Fragment {
            return CityFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View? = inflater.inflate(R.layout.fragment_city, container, false)
        view?.let { bindView(it) }
        searchCity()
        return view
    }

    private fun bindView(view: View) {
        editTextSearch = view.findViewById(R.id.editTextSearch)
    }

    private fun searchCity() {
        editTextSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                viewModel.getCityData(editTextSearch.text.toString())
            }
        })
    }
}

package com.e.weatherapp.utils

import android.content.Context
import android.widget.Toast

class Toast {
    companion object {
        fun message(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.core

import android.content.Context
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.getCityName(context: Context): String? {
    return try {
        val geocoder = Geocoder(context)
        val addresses = geocoder.getFromLocationName(this, 1)
        if (!addresses.isNullOrEmpty()) {
            val address = addresses[0]
            if (address.locality == null) {
                address.subAdminArea
            } else {
                address.locality
            }
        } else {
            ""
        }
    } catch (e: Exception) {
        null
    }

}


fun Context.showToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}


fun getIconUrl(icon: String): String = "https://openweathermap.org/img/w/${icon}.png"


fun Long.convertMillisToDate(): String {
    val formatter = SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH)
    val date = Date(this)
    return formatter.format(date)
}
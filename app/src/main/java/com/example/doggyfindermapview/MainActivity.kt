package com.example.doggyfindermapview

import android.R.layout
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions



// Implement OnMapReadyCallback.
class MainActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener {
    // Determines whether or not you have internet access and stores it in a boolean
    private val isConnected: Boolean
        get() {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

    private val dogMarker = LatLng(-31.952854, 115.857342)
    private var markerDog: Marker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout file as the content view.
        setContentView(R.layout.activity_main)














        findViewById<Button>(R.id.download_button)
            .setOnClickListener {
                onButtonShowPopupWindowClick(findViewById(R.id.download_button))
            }


        // Get a handle to the fragment and register the callback.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        // Check if you have internet access
        if (isConnected) {
            Toast.makeText(this, "Internet Access", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "No Internet Access Starting in offline mode", Toast.LENGTH_LONG).show()
        }

    }

    // Get a handle to the GoogleMap object and display marker.
    override fun onMapReady(googleMap: GoogleMap) {
        markerDog?.tag = 0
        markerDog = googleMap.addMarker(
            MarkerOptions()
                .position(dogMarker)
                .title("Dog Lat: " + dogMarker.latitude + ", Long: " + dogMarker.longitude)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true


    }

    override fun onMarkerClick(marker: Marker): Boolean {
        // Display where the dog is at in lat long
        val text = "Lat: " + marker.position.latitude + " Long: " + marker.position.longitude
        val duration = Toast.LENGTH_LONG

        val toast = Toast.makeText(this, text, duration) // in Activity
        toast.show()


        return false
    }

    fun onButtonShowPopupWindowClick(view: View?) {

        // inflate the layout of the popup window
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.popup_window, null)

        // create the popup window
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true // lets taps outside the popup also dismiss it
        val popupWindow = PopupWindow(popupView, width, height, focusable)

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        // dismiss the popup window when touched
        popupView.setOnTouchListener { v, event ->
            popupWindow.dismiss()
            true
        }
    }

}
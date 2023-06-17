package com.example.doggyfindermapview

import com.example.doggyfindermapview.R
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

// Implement OnMapReadyCallback.
class MainActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener {
    private val DOG = LatLng(-31.952854, 115.857342)
    private var markerDog: Marker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout file as the content view.
        setContentView(R.layout.activity_main)

        // Get a handle to the fragment and register the callback.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    // Get a handle to the GoogleMap object and display marker.
    override fun onMapReady(googleMap: GoogleMap) {
        markerDog?.tag = 0
        markerDog = googleMap.addMarker(
            MarkerOptions()
                .position(DOG)
                .title("Dog Lat: " + DOG.latitude + ", Long: " + DOG.longitude)
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


}
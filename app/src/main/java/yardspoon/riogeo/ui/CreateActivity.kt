package yardspoon.riogeo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber
import yardspoon.riogeo.R
import yardspoon.riogeo.misc.permissionGiven

class CreateActivity : AppCompatActivity(), OnMapReadyCallback, PlaceSelectionListener {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val mapFragment = findMapFragment()
        val searchFragment = findAutocompleteSearchFragment()

        searchFragment.setOnPlaceSelectedListener(this)

        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap.apply {
            if (permissionGiven(Manifest.permission.ACCESS_FINE_LOCATION)) {
                isMyLocationEnabled = true
            }

            val sydney = LatLng(-34.0, 151.0)
            addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }

    override fun onPlaceSelected(place: Place?) {
        place?.let {
            Timber.i("Autocomplete search resulted in place: $place")
            map.addMarker(MarkerOptions().position(place.latLng).title(place.name.toString()))
            map.animateCamera(CameraUpdateFactory.newLatLng(place.latLng))
        }
    }

    override fun onError(status: Status?) {
        Timber.e("Problem searching for place with autocomplete: $status")
    }

    private fun findMapFragment() = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    private fun findAutocompleteSearchFragment() = supportFragmentManager.findFragmentById(R.id.autocomplete_search) as SupportPlaceAutocompleteFragment

}

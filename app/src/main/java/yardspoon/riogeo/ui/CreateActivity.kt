package yardspoon.riogeo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber
import yardspoon.riogeo.R
import yardspoon.riogeo.misc.permissionGiven

private val WHITE_HOUSE_LOC = LatLng(38.8976805, -77.0387185)
private const val HIGH_ZOOM_LEVEL = 16.0f

class CreateActivity : AppCompatActivity(), OnMapReadyCallback, PlaceSelectionListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var marker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        findAutocompleteSearchFragment().setOnPlaceSelectedListener(this)
        findMapFragment().getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap.apply {
            if (permissionGiven(Manifest.permission.ACCESS_FINE_LOCATION)) {
                isMyLocationEnabled = true

                fusedLocationClient.lastLocation
                        .addOnSuccessListener { zoomMapTo(LatLng(it.latitude, it.longitude), null) }
                        .addOnFailureListener { zoomMapTo(WHITE_HOUSE_LOC, "The White House") }
            }
        }
    }

    override fun onPlaceSelected(place: Place?) {
        place?.apply {
            Timber.i("Autocomplete search resulted in place: $place")
            map.zoomMapTo(latLng, name.toString())
        }
    }

    private fun GoogleMap.zoomMapTo(placeLocation: LatLng, placeName: String? = null) {
        marker = (marker ?: addMarker(MarkerOptions().position(placeLocation).draggable(true))).apply {
            position = placeLocation
            title = placeName
        }

        animateCamera(CameraUpdateFactory.newLatLngZoom(placeLocation, HIGH_ZOOM_LEVEL))
    }

    // Place autocomplete
    override fun onError(status: Status?) {
        Timber.e("Problem searching for place with autocomplete: $status")
    }

    private fun findMapFragment() = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    private fun findAutocompleteSearchFragment() = supportFragmentManager.findFragmentById(R.id.autocomplete_search) as SupportPlaceAutocompleteFragment

}

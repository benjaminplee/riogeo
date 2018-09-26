package yardspoon.riogeo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber
import yardspoon.riogeo.R
import yardspoon.riogeo.misc.permissionGiven


class CreateActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val autocompleteFragment = (fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment)

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Timber.i("Found $place")
//                Log.i(FragmentActivity.TAG, "Place: " + place.name)
            }

            override fun onError(status: Status) {
                Timber.i("Error finding place $status")
//                Log.i(FragmentActivity.TAG, "An error occurred: $status")
            }
        })

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
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

}

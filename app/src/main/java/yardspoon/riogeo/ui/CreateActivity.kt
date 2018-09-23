package yardspoon.riogeo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import yardspoon.riogeo.R
import yardspoon.riogeo.misc.permissionGiven

class CreateActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
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

}

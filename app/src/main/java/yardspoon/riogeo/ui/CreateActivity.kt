package yardspoon.riogeo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_create.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import yardspoon.riogeo.R
import yardspoon.riogeo.biz.CreateViewModel
import yardspoon.riogeo.misc.permissionGiven

private const val PLACE_PICKER_REQUEST = 1

class CreateActivity : AppCompatActivity(), OnMapReadyCallback {

    private val vm: CreateViewModel by viewModel()

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        search.setOnClickListener {
            startActivityForResult(PlacePicker.IntentBuilder().build(this), PLACE_PICKER_REQUEST)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
            when (requestCode) {
                PLACE_PICKER_REQUEST -> processPlacePickerResult(resultCode, data)
                else -> super.onActivityResult(requestCode, resultCode, data)
            }

    private fun processPlacePickerResult(resultCode: Int, data: Intent?) {
        when (resultCode) {
            RESULT_OK -> {
                Toast.makeText(this, "Picked!", Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(this, "Problem with place picker result", Toast.LENGTH_LONG).show()
            }
        }
    }
}

package yardspoon.riogeo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import yardspoon.riogeo.R
import yardspoon.riogeo.consume

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_settings -> consume { showSettings() }
                R.id.nav_geofences -> consume { showGeofencesScreen() }
                R.id.nav_events -> consume { showEventsScreen() }
                R.id.nav_map -> consume { showMapScreen() }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.nav_geofences
        }
    }

    private fun showSettings() {

    }

    private fun showGeofencesScreen() {

    }

    private fun showEventsScreen() {

    }

    private fun showMapScreen() {

    }
}

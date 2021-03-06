package yardspoon.riogeo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import yardspoon.riogeo.R
import yardspoon.riogeo.misc.asTheTruth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_settings -> ::showSettings.asTheTruth
                R.id.nav_geofences -> ::showGeofencesScreen.asTheTruth
                R.id.nav_events -> ::showEventsScreen.asTheTruth
                R.id.nav_map -> ::showMapScreen.asTheTruth
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemReselectedListener {}

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.nav_geofences
        }
    }

    private fun showSettings() = swapScreen(SettingsFragment())
    private fun showGeofencesScreen() = swapScreen(GeofencesFragment())
    private fun showEventsScreen() = swapScreen(EventsFragment())
    private fun showMapScreen() = swapScreen(MapFragment())

    private fun swapScreen(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.screen_container, fragment)
                .commit()
    }
}

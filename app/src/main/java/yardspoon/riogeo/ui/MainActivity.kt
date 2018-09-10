package yardspoon.riogeo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import yardspoon.riogeo.R
import yardspoon.riogeo.asTheTruth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_settings -> { swapScreen(SettingsFragment()) }.asTheTruth
                R.id.nav_geofences -> { swapScreen(GeofencesFragment()) }.asTheTruth
                R.id.nav_events -> { swapScreen(EventsFragment()) }.asTheTruth
                R.id.nav_map -> { swapScreen(MapFragment()) }.asTheTruth
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemReselectedListener {}

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.nav_geofences
        }
    }

    private fun swapScreen(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.screen_container, fragment)
                .commit()
    }
}

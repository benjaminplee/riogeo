package yardspoon.riogeo.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import com.karumi.dexter.listener.single.CompositePermissionListener
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import kotlinx.android.synthetic.main.activity_onboarding.*
import yardspoon.riogeo.R
import yardspoon.riogeo.ui.main.MainActivity

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        launch.setOnClickListener {
            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(
                            CompositePermissionListener(
                                    SnackbarOnDeniedPermissionListener.Builder.with(launch, "Location access is required to monitor geofences.").build(),
                                    object : BasePermissionListener() {
                                        override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                                            launchMainActivity()
                                        }
                                    }
                            )
                    )
                    .check()
        }
    }

    private fun launchMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

}

package yardspoon.riogeo.misc

import android.app.Activity
import android.content.pm.PackageManager
import android.view.View
import androidx.core.content.ContextCompat

inline val (() -> Unit?).asTheTruth: Boolean
    get() {
        this()
        return true
    }

fun View.makeVisisbleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun Activity.permissionGiven(permission: String) = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
fun Activity.permissionNotGiven(permission: String) = ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
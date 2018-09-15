package yardspoon.riogeo.misc

import android.view.View

inline val (() -> Unit?).asTheTruth: Boolean
    get() {
        this()
        return true
    }

fun View.makeVisisbleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
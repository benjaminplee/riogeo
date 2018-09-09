package yardspoon.riogeo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_geofences.*
import yardspoon.riogeo.R

class GeofencesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_geofences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        empty_message.visibility = View.VISIBLE
    }
}

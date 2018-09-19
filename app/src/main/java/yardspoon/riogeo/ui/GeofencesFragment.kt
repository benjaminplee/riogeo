package yardspoon.riogeo.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_geofences.*
import kotlinx.android.synthetic.main.list_item_geofence.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import yardspoon.riogeo.R
import yardspoon.riogeo.biz.GeofencesViewModel
import yardspoon.riogeo.data.Geofence
import yardspoon.riogeo.misc.makeVisisbleIf

class GeofencesFragment : Fragment() {

    private val geofencesViewModel: GeofencesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_geofences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val geofenceListAdapter = GeofenceListAdapter()

        geofences_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = geofenceListAdapter
        }

        add_fab.setOnClickListener {
            startActivity(Intent(context, CreateActivity::class.java))
//            geofencesViewModel.add(Geofence(0, "foo", 1.0, 2.0, 3.0F, 100L, 1))
        }

        geofencesViewModel.data.observe(this, Observer { fences ->
            empty_message.makeVisisbleIf(fences.isEmpty())
            geofenceListAdapter.submitList(fences)
        })
    }
}

private class GeofenceListAdapter : ListAdapter<Geofence, GeofenceListAdapter.ViewHolder>(GeofenceDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_geofence, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(getItem(position))

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameView: TextView = view.item_name
        private val detailsView: TextView = view.item_details
        private val image: ImageView = view.item_image

        fun bind(geofence: Geofence) {
            geofence.apply {
                nameView.text = "$id - $name"
                detailsView.text = "${radius}m @ ${latitude}, ${longitude}"
            }
        }
    }

    private class GeofenceDiffUtilCallback : DiffUtil.ItemCallback<Geofence>() {
        override fun areItemsTheSame(oldItem: Geofence, newItem: Geofence): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Geofence, newItem: Geofence): Boolean = oldItem == newItem
    }

}




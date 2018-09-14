package yardspoon.riogeo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class GeofencesFragment : Fragment() {

    val geofencesViewModel: GeofencesViewModel by viewModel()

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

        }

        geofencesViewModel.data.observe(this, Observer { fences ->
            empty_message.visibility = if (fences.isEmpty()) View.VISIBLE else View.GONE
            geofenceListAdapter.submitList(fences)
        })
    }
}

private class GeofenceListAdapter : ListAdapter<Geofence, GeofenceListAdapter.ViewHolder>(GeofenceDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_geofence, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(getItem(position))

    private class ViewHolder(view: View, val nameView: TextView = view.name) : RecyclerView.ViewHolder(view) {
        fun bind(geofence: Geofence) {
            geofence.apply {
                nameView.text = "$id - $name"
            }
        }
    }

    private class GeofenceDiffUtilCallback : DiffUtil.ItemCallback<Geofence>() {
        override fun areItemsTheSame(oldItem: Geofence, newItem: Geofence): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Geofence, newItem: Geofence): Boolean = oldItem == newItem
    }

}




package yardspoon.riogeo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_geofences.*
import kotlinx.android.synthetic.main.list_item_geofence.view.*
import yardspoon.riogeo.R
import java.util.*

class GeofencesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_geofences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val datasource = mutableListOf("Foo", "Bar", "Baz")
        val geofenceListAdapter = GeofenceListAdapter(datasource)

        geofences_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = geofenceListAdapter
        }

        add_fab.setOnClickListener {
            datasource.add(0, Random().nextLong().toString())
            geofenceListAdapter.notifyItemInserted(0)
        }

        empty_message.visibility = if (datasource.isEmpty()) View.VISIBLE else View.GONE
    }
}

private class GeofenceListAdapter(private val datasource: List<String>) : RecyclerView.Adapter<GeofenceListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_geofence, parent, false))

    override fun getItemCount(): Int = datasource.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = datasource[position]
    }

    class ViewHolder(view: View, val name: TextView = view.name) : RecyclerView.ViewHolder(view)

}



package yardspoon.riogeo.biz

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import yardspoon.riogeo.data.AppDatabase
import yardspoon.riogeo.data.Geofence
import yardspoon.riogeo.data.GeofencesDAO

class GeofencesViewModel(database: AppDatabase) : ViewModel() {

    private val dao: GeofencesDAO = database.geofenceDao()

    val data: LiveData<List<Geofence>> = dao.selectAll()

    fun add(geofence: Geofence) {
        InsertTask(dao).execute(geofence)
    }

    // TODO move this to a repo
    private class InsertTask internal constructor(private val dao: GeofencesDAO) : AsyncTask<Geofence, Void, Void>() {
        override fun doInBackground(vararg params: Geofence): Void? {
            dao.insert(params[0])
            return null
        }
    }
}

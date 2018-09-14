package yardspoon.riogeo.biz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yardspoon.riogeo.data.AppDatabase
import yardspoon.riogeo.data.Geofence
import yardspoon.riogeo.data.GeofencesDAO

class GeofencesViewModel(database: AppDatabase) : ViewModel() {

    private val dao: GeofencesDAO = database.geofenceDao()
    private val mutableData: MutableLiveData<List<Geofence>> = dao.selectAll()

    val data: LiveData<List<Geofence>> = mutableData

    fun add() {
        val fences = mutableData.value?.toMutableList() ?: mutableListOf()
        fences.add(Geofence(0, "foo", 1.0, 2.0, 3.0F, 100L, 1))
        mutableData.value = fences
    }
}
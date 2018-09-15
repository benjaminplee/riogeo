package yardspoon.riogeo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GeofencesDAO {

    @Insert
    fun insert(geofence: Geofence)

    @Delete
    fun delete(geofence: Geofence)

    @Query("SELECT * FROM $TABLE_NAME_GEOFENCE ORDER BY id ASC")
    fun selectAll(): LiveData<List<Geofence>>

}
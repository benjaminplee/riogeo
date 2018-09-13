package yardspoon.riogeo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [Geofence::class],
        version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun geofenceDao(): GeofencesDAO
}

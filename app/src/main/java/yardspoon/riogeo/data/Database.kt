package yardspoon.riogeo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "riogeodb"

@Database(
        entities = [Geofence::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun geofenceDao(): GeofencesDAO

    companion object {
        fun create(context: Context): AppDatabase =
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }
}

package yardspoon.riogeo

import android.app.Application
import androidx.room.Room
import yardspoon.riogeo.data.Database

class RioGeoApp : Application() {

    lateinit var database: Database
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, Database::class.java, "riogeo").build()
    }
}
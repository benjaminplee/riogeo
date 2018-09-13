package yardspoon.riogeo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME_GEOFENCE = "geofences"

@Entity(tableName = TABLE_NAME_GEOFENCE)
data class Geofence(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "latitude")
        var latitude: Double,

        @ColumnInfo(name = "longitude")
        var longitude: Double,

        @ColumnInfo(name = "radius")
        var radius: Float,

        @ColumnInfo(name = "expiration_duration")
        var expirationDuration: Long,

        @ColumnInfo(name = "transitions")
        var transitionTypes: Int
)
package com.example.runningtracker.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "running_table")
data class Run(
    var image:Bitmap? = null,
    var timestamp: Long = 0L,
    var avgSpeed:Float = 0F,
    var distanceInMeters:Int=0,
    var timeInMillis:Long=0L,
    var calories:Int =0
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
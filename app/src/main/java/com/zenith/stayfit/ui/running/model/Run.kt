package com.zenith.stayfit.ui.running.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "run_table")
data class Run(
    @ColumnInfo(name = "run_image") var img:Bitmap? = null,
    @ColumnInfo(name = "run_timestamp") var timestamp: Long = 0L,
    @ColumnInfo(name = "run_average_speed") var avgSpeedInKMH: Float = 0f,
    @ColumnInfo(name = "run_distance_meters") var distanceInMeters: Int = 0,
    @ColumnInfo(name = "run_time_mills") var timeInMillis: Long = 0L,
    @ColumnInfo(name = "run_calories_burned") var caloriesBurned: Int = 0,
    @PrimaryKey @ColumnInfo(name = "run_primary_id") var run_id:String = UUID.randomUUID().toString()
) {
}
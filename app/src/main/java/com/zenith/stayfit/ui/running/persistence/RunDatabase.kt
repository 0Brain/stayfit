
package com.zenith.stayfit.ui.running.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zenith.stayfit.commons.Converters
import com.zenith.stayfit.ui.running.model.Run

@Database(
    entities = [Run::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RunDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunDao
}

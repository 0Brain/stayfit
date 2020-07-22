package com.zenith.stayfit.ui.running.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zenith.stayfit.commons.persistence.local.BaseDao
import com.zenith.stayfit.ui.running.model.Run


@Dao
abstract class RunDao:BaseDao<Run> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRun(run: Run)

    @Delete
    abstract suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM run_table ORDER BY run_timestamp DESC")
    abstract fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY run_time_mills DESC")
    abstract fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY run_calories_burned DESC")
    abstract fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY run_average_speed DESC")
    abstract fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY run_distance_meters DESC")
    abstract fun getAllRunsSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT SUM(run_time_mills) FROM run_table")
    abstract fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(run_calories_burned) FROM run_table")
    abstract fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(run_distance_meters) FROM run_table")
    abstract fun getTotalDistance(): LiveData<Int>

    @Query("SELECT AVG(run_average_speed) FROM run_table")
    abstract fun getTotalAvgSpeed(): LiveData<Float>
    
}
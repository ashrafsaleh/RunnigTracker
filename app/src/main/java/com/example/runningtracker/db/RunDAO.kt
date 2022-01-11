package com.example.runningtracker.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY timeInMillis DESC")
    fun getAllRunSortedByTime(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY calories DESC")
    fun getAllRunSortedByCalories(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeed DESC")
    fun getAllRunSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT SUM(timeInMillis) FROM running_table ")
    fun getTotalTime():LiveData<Long>

    @Query("SELECT SUM(distanceInMeters) FROM running_table ")
    fun getTotalDistance():LiveData<Int>

    @Query("SELECT SUM(calories) FROM running_table ")
    fun getTotalCalories():LiveData<Int>

    @Query("SELECT AVG(avgSpeed) FROM running_table ")
    fun getTotalAvgSpeed():LiveData<Float>


}
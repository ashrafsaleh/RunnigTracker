package com.example.runningtracker.repositories

import com.example.runningtracker.db.Run
import com.example.runningtracker.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)
    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)
    fun getRunByDate() = runDAO.getAllRunSortedByDate()
    fun getRunByDistance() = runDAO.getAllRunSortedByDistance()
    fun getRunByAvgSpeed() = runDAO.getAllRunSortedByAvgSpeed()
    fun getRunByCalories() = runDAO.getAllRunSortedByCalories()
    fun getRunByTime() = runDAO.getAllRunSortedByTime()
    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()

    fun getTotalDistance() = runDAO.getTotalDistance()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()

    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()
}
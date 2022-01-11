package com.example.runningtracker.repositories

import com.example.runningtracker.db.Run
import com.example.runningtracker.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)
    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)
    fun getRunByDate(run: Run) = runDAO.getAllRunSortedByDate()
    fun getRunByDistance(run: Run) = runDAO.getAllRunSortedByDistance()
    fun getRunByAvgSpeed(run: Run) = runDAO.getAllRunSortedByAvgSpeed()
    fun getRunByCalories(run: Run) = runDAO.getAllRunSortedByCalories()
    fun getRunByTime(run: Run) = runDAO.getAllRunSortedByTime()
    fun getTotalSpeed()=runDAO.getTotalAvgSpeed()
    fun getTotalDistance()=runDAO.getTotalDistance()
    fun getTotalCalories()=runDAO.getTotalCalories()
    fun getTotalTime()=runDAO.getTotalTime()
}
package com.example.runningtracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningtracker.db.Run
import com.example.runningtracker.others.SortType
import com.example.runningtracker.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val repository: MainRepository
) : ViewModel(){
    private val runSortedByDate = repository.getRunByDate()
    private val runSortedByDistance = repository.getRunByDistance()
    private val runSortedByAvgSpeed = repository.getRunByAvgSpeed()
    private val runSortedByTime = repository.getRunByTime()
    private val runSortedByCalories = repository.getRunByCalories()

    val runs = MediatorLiveData<List<Run>>()
    var sortType = SortType.DATE
    init {
        runs.addSource(runSortedByDate){result->
            if(sortType == SortType.DATE){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedByDistance){result->
            if(sortType == SortType.DISTANCE){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedByAvgSpeed){result->
            if(sortType == SortType.AVG_SPEED){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedByTime){result->
            if(sortType == SortType.TIME){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runSortedByCalories){result->
            if(sortType == SortType.CALORIES_BURNED){
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when (sortType){
        SortType.DATE-> runSortedByDate.value?.let { runs.value=it }
        SortType.DISTANCE-> runSortedByDistance.value?.let { runs.value=it }
        SortType.AVG_SPEED-> runSortedByAvgSpeed.value?.let { runs.value=it }
        SortType.TIME-> runSortedByTime.value?.let { runs.value=it }
        SortType.CALORIES_BURNED-> runSortedByCalories.value?.let { runs.value=it }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run:Run)= viewModelScope.launch {
        repository.insertRun(run)
    }
}
package com.sidukov.fitnesskittest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidukov.fitnesskittest.data.ScheduleRepository
import com.sidukov.fitnesskittest.domain.TimeStamp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ScheduleViewModel(private val repository: ScheduleRepository) : ViewModel() {

    private val _fitnessData = MutableSharedFlow<List<TimeStamp>>()
    var fitnessData = _fitnessData.asSharedFlow()

    init {
        viewModelScope.launch {
            _fitnessData.emit(
                repository.getScheduleData()
            )
        }
    }

}
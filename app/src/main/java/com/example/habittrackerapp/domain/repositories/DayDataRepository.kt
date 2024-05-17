package com.example.habittrackerapp.domain.repositories


import com.example.habittrackerapp.domain.model.CalendarDayData
import com.example.habittrackerapp.domain.utils.states.DataState
import kotlinx.coroutines.flow.Flow

interface DayDataRepository {

    suspend fun getProgressDataForMonth(month: String) : Flow<DataState<List<CalendarDayData>>>
    suspend fun addHabit(dayData: CalendarDayData)
}
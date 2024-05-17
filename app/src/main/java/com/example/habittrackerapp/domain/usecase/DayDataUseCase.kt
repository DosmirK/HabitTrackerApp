package com.example.habittrackerapp.domain.usecase

import com.example.habittrackerapp.data.repositories.DayDataRepositoryImpl
import com.example.habittrackerapp.domain.model.CalendarDayData
import com.example.habittrackerapp.domain.utils.states.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DayDataUseCase @Inject constructor(
    private val dayDataRepository: DayDataRepositoryImpl
) {
    suspend fun getDataForMonth(month: String): Flow<DataState<List<CalendarDayData>>> = dayDataRepository.getProgressDataForMonth(month)
    suspend fun addDayData(dayData: CalendarDayData) = dayDataRepository.addHabit(dayData)
}
package com.example.habittrackerapp.data.repositories

import android.util.Log
import com.example.habittrackerapp.data.db.dao.DayDataDao
import com.example.habittrackerapp.data.db.madel.DayData
import com.example.habittrackerapp.domain.model.CalendarDayData
import com.example.habittrackerapp.domain.repositories.DayDataRepository
import com.example.habittrackerapp.domain.utils.converters.mapToCalendarDayData
import com.example.habittrackerapp.domain.utils.converters.toDayData
import com.example.habittrackerapp.domain.utils.states.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DayDataRepositoryImpl @Inject constructor(
    private val dayDataDao: DayDataDao
): DayDataRepository {
    override suspend fun getProgressDataForMonth(month: String): Flow<DataState<List<CalendarDayData>>> =
        flow {
            emit(DataState.Loading())
            try {
                val data = dayDataDao.getProgressDataForMonth(month).mapToCalendarDayData()
                emit(DataState.Success(data))
                Log.e("ololo", "dataRepost: $data")
            } catch (e: Exception) {
                emit(DataState.Error(e.localizedMessage))
                Log.e("ololo", "errorRepost: ${e.localizedMessage}")
            }
        }

    override suspend fun addHabit(dayData: CalendarDayData) =
        dayDataDao.addDayData(dayData.toDayData())
}
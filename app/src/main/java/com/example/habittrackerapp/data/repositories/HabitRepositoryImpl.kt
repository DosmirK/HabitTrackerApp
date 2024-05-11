package com.example.habittrackerapp.data.repositories

import android.util.Log
import com.example.habittrackerapp.data.db.dao.HabitDao
import com.example.habittrackerapp.domain.model.HabitModel
import com.example.habittrackerapp.domain.repositories.HabitRepository
import com.example.habittrackerapp.domain.utils.DataState
import com.example.habittrackerapp.domain.utils.mapToHabitModel
import com.example.habittrackerapp.domain.utils.toHabit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
): HabitRepository {
    override suspend fun getAllHabits(): Flow<DataState<List<HabitModel>>> =
        flow {
            emit(DataState.Loading())
            try {
                val data = habitDao.getAllHabits().mapToHabitModel()
                emit(DataState.Success(data))
                Log.e("ololo", "dataRepost: $data")
            } catch (e: Exception) {
                emit(DataState.Error(e.localizedMessage))
                Log.e("ololo", "errorRepost: ${e.localizedMessage}")
            }
        }

    override suspend fun addHabit(habit: HabitModel) = habitDao.addHabit(habit.toHabit())
}
package com.example.habittrackerapp.domain.repositories

import com.example.habittrackerapp.domain.model.HabitModel
import com.example.habittrackerapp.domain.utils.states.DataState
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun getAllHabits() : Flow<DataState<List<HabitModel>>>
    suspend fun addHabit(habit: HabitModel)
    suspend fun updateHabit(habit: HabitModel)
    suspend fun deleteHabit(habit: HabitModel)
    suspend fun getTotalHabitsCount(): Int
    suspend fun getCompletedHabitsCount(): Int
}
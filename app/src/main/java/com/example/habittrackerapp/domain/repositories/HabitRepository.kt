package com.example.habittrackerapp.domain.repositories

import com.example.habittrackerapp.domain.model.HabitModel
import com.example.habittrackerapp.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun getAllHabits() : Flow<DataState<List<HabitModel>>>

    suspend fun addHabit(habit: HabitModel)
}
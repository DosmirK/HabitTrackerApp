package com.example.habittrackerapp.domain.usecase

import com.example.habittrackerapp.domain.repositories.HabitRepository
import javax.inject.Inject

class UpdateHabit @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend fun updateHabit() = habitRepository
}
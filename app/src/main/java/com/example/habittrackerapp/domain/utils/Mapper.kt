package com.example.habittrackerapp.domain.utils

import com.example.habittrackerapp.data.db.madel.Habit
import com.example.habittrackerapp.domain.model.HabitModel

fun List<Habit>.mapToHabitModel() = this.map {
    HabitModel(
        id = it.id,
        name = it.name,
        isCompleted = it.isCompleted
    )
}
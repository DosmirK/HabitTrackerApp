package com.example.habittrackerapp.domain.utils

import com.example.habittrackerapp.data.db.madel.Habit
import com.example.habittrackerapp.domain.model.HabitModel

fun HabitModel.toHabit(): Habit {
    return Habit(
        name = name,
        isCompleted = isCompleted
    )
}
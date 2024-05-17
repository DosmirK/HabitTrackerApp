package com.example.habittrackerapp.domain.utils.converters

import com.example.habittrackerapp.data.db.madel.DayData
import com.example.habittrackerapp.data.db.madel.Habit
import com.example.habittrackerapp.domain.model.CalendarDayData
import com.example.habittrackerapp.domain.model.HabitModel

fun HabitModel.toHabit(): Habit {
    return Habit(
        id = id,
        name = name,
        isCompleted = isCompleted
    )
}

fun CalendarDayData.toDayData(): DayData {
    return DayData(
        id = id,
        date = day,
        progress = progress
    )
}
package com.example.habittrackerapp.domain.utils.converters

import com.example.habittrackerapp.data.db.madel.DayData
import com.example.habittrackerapp.data.db.madel.Habit
import com.example.habittrackerapp.domain.model.CalendarDayData
import com.example.habittrackerapp.domain.model.HabitModel

fun List<Habit>.mapToHabitModel() = this.map {
    HabitModel(
        id = it.id,
        name = it.name,
        isCompleted = it.isCompleted
    )
}

fun List<DayData>.mapToCalendarDayData() = this.map {
    CalendarDayData(
        id = it.id,
        day = it.date,
        progress = it.progress
    )
}
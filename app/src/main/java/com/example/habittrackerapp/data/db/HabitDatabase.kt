package com.example.habittrackerapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habittrackerapp.data.db.dao.DayDataDao
import com.example.habittrackerapp.data.db.dao.HabitDao
import com.example.habittrackerapp.data.db.madel.DayData
import com.example.habittrackerapp.data.db.madel.Habit
import java.time.LocalDate

@Database(
    entities = [Habit::class, DayData::class ], version = 1
)
abstract class HabitDatabase: RoomDatabase() {
    abstract fun getHabitDao(): HabitDao
    abstract fun getDayDataDao(): DayDataDao
}
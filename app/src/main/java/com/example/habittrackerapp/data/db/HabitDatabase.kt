package com.example.habittrackerapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habittrackerapp.data.db.dao.HabitDao
import com.example.habittrackerapp.data.db.madel.Habit

@Database(
    entities = [Habit::class, ], version = 1
)
abstract class HabitDatabase: RoomDatabase() {

    abstract fun getHabitDao(): HabitDao
}
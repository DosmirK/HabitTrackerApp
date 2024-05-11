package com.example.habittrackerapp.data.db

import android.content.Context
import androidx.room.Room
import com.example.habittrackerapp.data.db.dao.HabitDao

object DatabaseManager {
    lateinit var habitDao: HabitDao

    fun init(context: Context) {
        val db = Room.databaseBuilder(
            context = context,
            klass = HabitDatabase::class.java,
            name = "my_database"
        ).allowMainThreadQueries().build()

        habitDao = db.getHabitDao()
    }
}
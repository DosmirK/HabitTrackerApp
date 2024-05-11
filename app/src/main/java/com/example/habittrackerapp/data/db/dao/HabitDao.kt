package com.example.habittrackerapp.data.db.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habittrackerapp.data.db.madel.Habit
import com.example.habittrackerapp.domain.model.HabitModel

@Dao
interface HabitDao {

    @Insert
    suspend fun addHabit(habit: Habit)

    @Delete
    suspend fun remove(habit: Habit)

    @Update
    fun update(habit: Habit)

    @Query("SELECT * FROM habits")
    suspend fun getAllHabits(): List<Habit>

}
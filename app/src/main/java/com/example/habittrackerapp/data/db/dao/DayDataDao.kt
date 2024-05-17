package com.example.habittrackerapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habittrackerapp.data.db.madel.DayData
import com.example.habittrackerapp.data.db.madel.Habit

@Dao
interface DayDataDao {

    @Query("SELECT * FROM dayData WHERE strftime('%Y-%m', date) = :month")
    suspend fun getProgressDataForMonth(month: String): List<DayData>

    @Insert
    suspend fun addDayData(dayData: DayData)

}
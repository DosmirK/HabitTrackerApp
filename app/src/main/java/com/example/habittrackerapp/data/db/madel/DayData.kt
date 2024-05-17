package com.example.habittrackerapp.data.db.madel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "dayData")
data class DayData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val progress: Int
)

package com.example.habittrackerapp.domain.utils.calendar

import android.util.Log
import com.example.habittrackerapp.data.db.madel.DayData
import com.example.habittrackerapp.domain.model.CalendarDayData
import java.time.LocalDate
import java.time.YearMonth

class CalendarHelper {

    fun dayDataInMonthList(selectedDate: LocalDate, progressMap: Map<LocalDate, Int>): List<CalendarDayData> {
        Log.e("ololo", "progressMap in Helper: $progressMap")
        val dayDataList = mutableListOf<CalendarDayData>()
        val yearMonth = YearMonth.from(selectedDate)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                dayDataList.add(CalendarDayData(id = i, day = "", progress = 0))
            } else {
                val dayOfMonth = i - dayOfWeek
                if (dayOfMonth <= daysInMonth) {
                    val dayKay = selectedDate.withDayOfMonth(dayOfMonth)
                    val day = i - dayOfWeek
                    val progress = progressMap[dayKay] ?: 0
                    val dayData = CalendarDayData(i, day.toString(), progress)
                    dayDataList.add(dayData)
                } else {
                    dayDataList.add(CalendarDayData(id = i, day = "", progress = 0))
                }
            }
        }

        /*for (i in 1..daysInMonth) {
            val day = selectedDate.withDayOfMonth(i)
            val progress = progressMap[day] ?: 0 // Получаем прогресс из карты или устанавливаем 0 по умолчанию
            val dayData = CalendarDayData(i, day.toString(), progress)
            dayDataList.add(dayData)
        }*/

        return dayDataList
    }

        fun createDayDataInMonthList(selectedDate: LocalDate, progressMap: Map<LocalDate, Int>): List<CalendarDayData> {
            Log.e("ololo", "Создаём структуру календаря")
            val dayDataList = mutableListOf<CalendarDayData>()
            val yearMonth = YearMonth.from(selectedDate)
            val daysInMonth = yearMonth.lengthOfMonth()
            val firstOfMonth = selectedDate.withDayOfMonth(1)
            val dayOfWeek = firstOfMonth.dayOfWeek.value

            for(i in 1..42) {
                if(i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                    dayDataList.add(CalendarDayData(id = i, day = "", progress = 0))
                }else{
                    val day = i - dayOfWeek
                    val progress = 0
                    val dayData = CalendarDayData(i, day.toString(), progress)
                    dayDataList.add(dayData)
                }
            }
            Log.e("ololo", "Структурированный календарь: $dayDataList")
            return dayDataList
        }
}
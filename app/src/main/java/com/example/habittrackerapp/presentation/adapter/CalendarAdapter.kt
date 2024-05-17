package com.example.habittrackerapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.habittrackerapp.databinding.CalendarDayItemBinding
import com.example.habittrackerapp.domain.model.CalendarDayData

class CalendarAdapter : ListAdapter<CalendarDayData, CalendarAdapter.CalendarViewHolder>(
    object : DiffUtil.ItemCallback<CalendarDayData>() {
        override fun areItemsTheSame(oldItem: CalendarDayData, newItem: CalendarDayData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CalendarDayData, newItem: CalendarDayData) =
            oldItem.id == newItem.id
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = CalendarDayItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        Log.e("ololo", "CalendarViewHolder создан: $binding")
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.onBind(getItem(position))
        Log.e("ololo", "Привяска данных работает")
    }

    inner class CalendarViewHolder(
        private val binding: CalendarDayItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(dayData: CalendarDayData) {
            Log.e("ololo", "видемый календарь: $dayData")
            binding.dayTextView.text = dayData.day
            binding.myProgress.progress = dayData.progress
            /*binding.dayTextView.text = dayData.day
            val progressTv = dayData.progress.toString() + "%"
            binding.progressTv.text = progressTv
            binding.myProgress.progress = dayData.progress*/
        }
    }
}

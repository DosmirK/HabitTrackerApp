package com.example.habittrackerapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.habittrackerapp.data.db.DatabaseManager
import com.example.habittrackerapp.databinding.ItemHabitBinding
import com.example.habittrackerapp.domain.model.HabitModel

class HabitsAdapter: ListAdapter<HabitModel, HabitsAdapter.HabitViewHolder>(
    object : DiffUtil.ItemCallback<HabitModel>(){
        override fun areItemsTheSame(oldItem: HabitModel, newItem: HabitModel)
                = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: HabitModel, newItem: HabitModel)
                = oldItem.name == newItem.name

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class HabitViewHolder(
        private val binding: ItemHabitBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(habit: HabitModel) {
            binding.apply {
                tvHabit.text = habit.name
                binding.cbDone.isChecked = habit.isCompleted
                /*binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                    DatabaseManager.habitDao.update(habit.copy(isCompleted = isChecked))
                }*/
            }
        }
    }
}
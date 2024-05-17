package com.example.habittrackerapp.presentation.fragments.habit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.habittrackerapp.databinding.FragmentHabitEditBinding
import com.example.habittrackerapp.presentation.viewmadel.HabitViewModel
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HabitEditFragment : Fragment() {

    private var _binding: FragmentHabitEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitViewModel by viewModels()
    private val args: HabitEditFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.habit?.let {
            val habit = it
            binding.editText.setText(habit.name.toString())
        }
        habitUpdate()
        deleteHabit()
    }

    private fun deleteHabit() {
        binding.deleteBtn.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                args.habit?.let {
                    val habit = it
                    viewModel.deleteHabit(habit)

                }
            }
            findNavController().navigateUp()
        }
    }

    private fun habitUpdate() {
        binding.updateBtn.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                   args.habit?.let {
                       val habit = it
                       val name = binding.editText.text.toString()
                       viewModel.updateHabit(habit.copy(id = habit.id, name = name, isCompleted = habit.isCompleted))

                   }
            }
            findNavController().navigateUp()
        }
    }
}
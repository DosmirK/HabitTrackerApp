package com.example.habittrackerapp.presentation.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.habittrackerapp.databinding.FragmentHomeBinding
import com.example.habittrackerapp.domain.model.HabitModel
import com.example.habittrackerapp.domain.utils.states.UiState
import com.example.habittrackerapp.presentation.adapter.HabitsAdapter
import com.example.habittrackerapp.presentation.viewmadel.HabitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitViewModel by viewModels()
    private val habitAdapter = HabitsAdapter(itemUpdated = this::habitUpdate, clickIsChecked = this::clickCheckBox )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
        initView()
        initListeners()
        updateProgress()
    }

    private fun initListeners() {
        binding.btnAddHabit.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToHabitFragment())
        }
    }

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllHabits()
            viewModel.habits.collect { result ->
                Log.e("ololo", "data: ${result.data}")
                when (result) {
                    is UiState.Loading -> binding.animLoading.visibility = View.VISIBLE

                    is UiState.Success -> {
                        habitAdapter.submitList(result.data)
                        binding.animLoading.visibility = View.GONE
                    }

                    is UiState.Empty -> Toast.makeText(requireContext(), "Data is empty",
                        Toast.LENGTH_LONG
                    ).show()

                    is UiState.Error -> Toast.makeText(requireContext(), result.message ?: "Unknown error",
                        Toast.LENGTH_LONG
                    ).show()

                    else -> {}
                }
            }
        }
    }

    private fun updateProgress(){
        viewLifecycleOwner.lifecycleScope.launch {
            binding.progressBar.progress = viewModel.percentageHabitsCompleted()
            Log.e("ololo", "progressBar: ${binding.progressBar.progress}")
        }
    }

    private fun initView() {
        binding.rvHabit.adapter = habitAdapter
    }
    private fun habitUpdate(habit: HabitModel){
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToHabitEditFragment(habit))
    }

    private fun clickCheckBox(habit: HabitModel){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.updateHabit(
                habit.copy(
                    isCompleted = habit.isCompleted,
                    id = habit.id,
                    name = habit.name
                )
            )
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.habits.collect { newData ->
                    when (newData) {
                        is UiState.Loading -> binding.animLoading.visibility = View.VISIBLE

                        is UiState.Success -> {
                            habitAdapter.submitList(newData.data)
                            binding.animLoading.visibility = View.GONE
                        }

                        is UiState.Empty -> Toast.makeText(
                            requireContext(), "Data is empty",
                            Toast.LENGTH_LONG
                        ).show()

                        is UiState.Error -> Toast.makeText(
                            requireContext(), newData.message ?: "Unknown error",
                            Toast.LENGTH_LONG
                        ).show()

                        else -> {}
                    }
                }
            }
            Log.e("ololo", "update: $habit")
        }
        updateProgress()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
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
import com.example.habittrackerapp.domain.utils.UiState
import com.example.habittrackerapp.presentation.adapter.HabitsAdapter
import com.example.habittrackerapp.presentation.viewmadel.HabitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitViewModel by viewModels()
    private val habitAdapter = HabitsAdapter()


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

        initView()
        setupData()
        initListeners()
    }

    private fun initListeners() {
        binding.btnAddHabit.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToHabitFragment())
        }
    }

    private fun setupData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllHabits()
            viewModel.habits.collect {
                Log.e("ololo", "data: ${it.data}")
                when (it) {
                    is UiState.Loading -> Toast.makeText(
                        requireContext(),
                        "Loading!",
                        Toast.LENGTH_LONG
                    ).show()

                    is UiState.Success -> {
                        habitAdapter.submitList(it.data)
                    }

                    is UiState.Empty -> Toast.makeText(
                        requireContext(),
                        "Data is empty",
                        Toast.LENGTH_LONG
                    ).show()

                    is UiState.Error -> Toast.makeText(
                        requireContext(),
                        it.message ?: "Unknown error",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }

    private fun initView() {
        binding.rvHabit.adapter = habitAdapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
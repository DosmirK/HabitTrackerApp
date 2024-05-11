package com.example.habittrackerapp.presentation.fragments.habit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.habittrackerapp.databinding.FragmentHabitBinding
import com.example.habittrackerapp.domain.model.HabitModel
import com.example.habittrackerapp.presentation.viewmadel.HabitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HabitFragment : Fragment() {

    private var _binding: FragmentHabitBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addHabit(
                HabitModel(
                    name = binding.editText.text.toString(),
                    isCompleted = false,
                    id = 0
                )
            )
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
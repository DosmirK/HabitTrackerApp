package com.example.habittrackerapp.presentation.fragments.progress

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.habittrackerapp.databinding.FragmentProgressBinding
import com.example.habittrackerapp.domain.model.CalendarDayData
import com.example.habittrackerapp.domain.utils.calendar.CalendarHelper
import com.example.habittrackerapp.domain.utils.states.UiState
import com.example.habittrackerapp.presentation.adapter.CalendarAdapter
import com.example.habittrackerapp.presentation.viewmadel.DayDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DayDataViewModel by viewModels()
    private val calendarAdapter = CalendarAdapter()
    private var selectedDate = LocalDate.now()
    private var progressData = mutableListOf<CalendarDayData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addProgress()
        setupData(selectedDate)
        setMonthView()
        initView()
        //initListeners()
    }

    private fun addProgress() {

        viewModel.viewModelScope.launch {
            viewModel.addDayData(
                CalendarDayData(
                    day = "2024-05-12",
                    progress = 100,
                    id = 0
                )
            )
        }
    }

    private fun setMonthView() {

        binding.monthYearTV.text = (monthYearFromDate(selectedDate))
        val calendarHelper = CalendarHelper()
        val progressMap = createProgressMap(progressData)
        val dataList: List<CalendarDayData> = calendarHelper.dayDataInMonthList(selectedDate, progressMap)
        calendarAdapter.submitList(dataList)
        Log.e("ololo", "Данные в хелпер: $dataList")
    }

    private fun createProgressMap(progressData: List<CalendarDayData>): Map<LocalDate, Int> {
        val progressMap = mutableMapOf<LocalDate, Int>()
        for (dayData in progressData) {
            Log.e("ololo", "данные в карте: ${dayData.day}")
            val date = LocalDate.parse(dayData.day)
            progressMap[date] = dayData.progress
        }
        Log.e("ololo", "progressMap: $progressMap")
        return progressMap
    }

    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    private fun setupData(data: LocalDate) {
        viewLifecycleOwner.lifecycleScope.launch {
            val month = "${data.year}-${String.format("%02d", data.monthValue)}"
            Log.e("ololo", "monthCalendar: $month")
            viewModel.getDataForMonth(month)
            viewModel.dayData.collect { result ->
                Log.e("ololo", "dataCalendar: ${result.data}")
                result.data?.let {
                    progressData = result.data as MutableList<CalendarDayData>
                }
            }
        }
    }

    private fun initView() {
        val layoutManager = GridLayoutManager(requireContext(), 7)
        binding.rvCalendar.layoutManager = layoutManager
        binding.rvCalendar.adapter = calendarAdapter
        Log.e("ololo", "адаптер передан в ресайклер")
    }

    private fun initListeners() {
        binding.btnNextMonth.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }

        binding.btnPreviousMonth.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
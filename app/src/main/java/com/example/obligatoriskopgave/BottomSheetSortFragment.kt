package com.example.obligatoriskopgave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.obligatoriskopgave.databinding.FragmentBottomSheetSortBinding
import com.example.obligatoriskopgave.models.PersonViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetSortFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetSortBinding? = null
    private val binding get() = _binding!!
    private val personViewModel: PersonViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sortName.setOnClickListener {
            personViewModel.sortByName()
            dismiss()
        }

        binding.sortNameDsc.setOnClickListener {
            personViewModel.sortByNameDes()
            dismiss()
        }

        binding.sortAge.setOnClickListener {
            personViewModel.sortByAge()
            dismiss()
        }

        binding.sortAgeDsc.setOnClickListener {
            personViewModel.sortByAgeDes()
            dismiss()
        }

        binding.sortYear.setOnClickListener {
            personViewModel.sortByYear()
            dismiss()
        }

        binding.sortYearDsc.setOnClickListener {
            personViewModel.sortByYearDes()
            dismiss()
        }

        binding.sortMonth.setOnClickListener {
            personViewModel.sortByMonth()
            dismiss()
        }

        binding.sortMonthDsc.setOnClickListener {
            personViewModel.sortByMonthDes()
            dismiss()
        }

        binding.sortDay.setOnClickListener {
            personViewModel.sortByDay()
            dismiss()
        }

        binding.sortDayDsc.setOnClickListener {
            personViewModel.sortByDayDes()
            dismiss()
        }

        binding.closeBottomSheet.setOnClickListener {
            dismiss()
        }
    }
}
package com.example.obligatoriskopgave

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.obligatoriskopgave.databinding.FragmentBottomSheetUpdateBinding
import com.example.obligatoriskopgave.models.Person
import com.example.obligatoriskopgave.models.PersonViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BottomSheetUpdateFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetUpdateBinding? = null
    private val binding get() = _binding!!
    private val calender = Calendar.getInstance()
    private var newPerson: Person = Person()
    private val personViewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        newPerson = arguments?.getParcelable("person")!!

        binding.personName.setText(newPerson.name)

        val selectedDate: Calendar = Calendar.getInstance()
        selectedDate.set(newPerson.birthYear,newPerson.birthDayOfMonth,newPerson.birthDayOfMonth)
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(selectedDate.time)
        binding.birthday.setText(formattedDate)

        binding.birthday.setOnClickListener {
            showDatePicker()
        }

        binding.personRemarks.setText(newPerson.remarks)

        binding.closeBottomSheet.setOnClickListener {
            dismiss()
        }

        binding.updatePersonButton.setOnClickListener {
            newPerson.name = binding.personName.text.toString().trim()
            newPerson.remarks = binding.personRemarks.text.toString().trim()
            personViewModel.update(newPerson)
            Toast.makeText(
                this.context,
                "Person updated",
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this.requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate: Calendar = Calendar.getInstance()
                selectedDate.set(year,monthOfYear,dayOfMonth)
                val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.birthday.setText(formattedDate)
                newPerson.birthYear = year
                newPerson.birthMonth = monthOfYear
                newPerson.birthDayOfMonth = dayOfMonth
            },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}
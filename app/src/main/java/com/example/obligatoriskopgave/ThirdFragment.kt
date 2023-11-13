package com.example.obligatoriskopgave

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.obligatoriskopgave.databinding.FragmentThirdBinding
import com.example.obligatoriskopgave.models.Person
import com.example.obligatoriskopgave.models.PersonViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private val auth = Firebase.auth
    private val calender = Calendar.getInstance()
    private var newPerson: Person = Person()
    private val personViewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.birthday.setOnClickListener {
            showDatePicker()
        }

        binding.addPersonButton.setOnClickListener {
            newPerson.userId = auth.currentUser!!.email
            newPerson.name = binding.name.text.toString().trim()
            personViewModel.add(newPerson)
            Toast.makeText(
                this.context,
                "New person added",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
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
package com.example.obligatoriskopgave

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.obligatoriskopgave.databinding.FragmentSecondBinding
import com.example.obligatoriskopgave.models.PersonViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val personViewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireArguments()
        val secondFragmentArgs: SecondFragmentArgs = SecondFragmentArgs.fromBundle(bundle)
        val position = secondFragmentArgs.position
        val person = personViewModel[position]
        if (person == null) {
            Toast.makeText(
                this.context,
                "No such person",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        binding.name.text = person.name
        binding.id.text = "Id: ${person.id}"
        binding.userId.text = "UserId: ${person.userId}"
        binding.birthday.text = "Date of birth: ${person.birthDayOfMonth}/${person.birthMonth}/${person.birthYear}"
        if (!person.remarks.isNullOrEmpty()) {
            binding.remarks.text = "Remarks: ${person.remarks}"
        } else {
            binding.remarks.visibility = View.GONE
        }
        binding.age.text = "Age: ${person.age}"

        binding.deleteButton.setOnClickListener{
            personViewModel.delete(person.id)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
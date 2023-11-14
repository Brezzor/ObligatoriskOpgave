package com.example.obligatoriskopgave

import android.annotation.SuppressLint
import android.app.AlertDialog
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
    private val bottomSheetUpdateFragment = BottomSheetUpdateFragment()
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

        val argsBundle = requireArguments()
        val secondFragmentArgs: SecondFragmentArgs = SecondFragmentArgs.fromBundle(argsBundle)
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
            val alert = AlertDialog.Builder(context)
            alert.setTitle("Delete Alert !")
            alert.setMessage("Are you sure you want to delete this person?")
            alert.setCancelable(false)
            alert.setPositiveButton("Yes") { dialog, which ->
                personViewModel.delete(person.id)
                dialog.dismiss()
                findNavController().popBackStack()
            }
            alert.setNegativeButton("No") { dialog, which ->
                dialog.cancel()
            }
            alert.show()
        }

        personViewModel.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty())
            {
                Toast.makeText(
                    this.context,
                    errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }



        binding.updateButton.setOnClickListener {
            val personBundle = Bundle()
            personBundle.putParcelable("person", person)
            bottomSheetUpdateFragment.arguments = personBundle
            bottomSheetUpdateFragment.show(parentFragmentManager, bottomSheetUpdateFragment.tag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
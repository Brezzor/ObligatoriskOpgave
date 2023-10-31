package com.example.obligatoriskopgave

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.obligatoriskopgave.databinding.FragmentFirstBinding
import com.example.obligatoriskopgave.models.DataAdapter
import com.example.obligatoriskopgave.models.PersonViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val personViewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personViewModel.personLiveData.observe(viewLifecycleOwner) { persons ->
            binding.recyclerView.visibility = if (persons == null) View.GONE else View.VISIBLE
            if (persons != null) {
                val adapter = DataAdapter(persons) { position ->
                    val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(position)
                    findNavController().navigate(action)
                }
                var colums = 2
                val currentOrientation = this.resources.configuration.orientation
                if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    colums = 4
                } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    colums = 2
                }
                binding.recyclerView.layoutManager = GridLayoutManager(this.context, colums)
                binding.recyclerView.adapter = adapter
                binding.swipeRefresh.isRefreshing = false
            }
        }

        personViewModel.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage ->
            binding.textViewError.text = errorMessage
        }

        personViewModel.reload()

        binding.swipeRefresh.setOnRefreshListener {
            personViewModel.reload()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.textViewTitle.text = "Welcome! " + auth.currentUser?.email + " to page one"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
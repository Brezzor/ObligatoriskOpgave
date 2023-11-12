package com.example.obligatoriskopgave

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.obligatoriskopgave.databinding.FragmentFirstBinding
import com.example.obligatoriskopgave.models.PersonAdapter
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
                val adapter = PersonAdapter(persons) { position ->
                    val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(position)
                    findNavController().navigate(action)
                }
                var colums = 1
                val currentOrientation = this.resources.configuration.orientation
                if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    colums = 2
                } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    colums = 1
                }
                binding.recyclerView.layoutManager = GridLayoutManager(this.context, colums)
                binding.recyclerView.adapter = adapter
                binding.swipeRefresh.isRefreshing = false
            }
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

        binding.swipeRefresh.setOnRefreshListener {
            personViewModel.reload(auth.currentUser!!.email!!)
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    personViewModel.reload(auth.currentUser!!.email!!)
                }
                personViewModel.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    personViewModel.reload(auth.currentUser!!.email!!)
                }
                personViewModel.filter(newText)
                return false
            }

        })

        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
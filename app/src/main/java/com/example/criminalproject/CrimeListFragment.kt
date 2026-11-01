package com.example.criminalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criminalproject.Model.Crime
import com.example.criminalproject.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class CrimeListFragment : Fragment() {
    private var _binding: FragmentCrimeListBinding? = null
    private val binding get() = _binding!!
    private val crimeListViewModel: CrimeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(inflater, container, false)
        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                crimeListViewModel.crimes.collect { crimes ->
                    binding.crimeRecyclerView.adapter = CrimeListAdapter(crimes) { crimeId ->
                        findNavController().navigate(CrimeListFragmentDirections.showCrimeDetail(crimeId))
                    }
                }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
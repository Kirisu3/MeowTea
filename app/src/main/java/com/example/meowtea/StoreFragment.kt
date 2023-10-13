package com.example.meowtea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meowtea.database.AppDatabase
import com.example.meowtea.databinding.FragmentStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding

    private val milkTeaDatabase: AppDatabase by lazy {
        AppDatabase.create(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (view == null) {
            binding = FragmentStoreBinding.inflate(inflater, container, false)
        }
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val milkTeaAdapter = MilkTeaAdapter(ArrayList()) // Initialize with an empty list
        val milkTeaDao = milkTeaDatabase.milkTeaDao()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            // Fetch the milk teas from the database on a background thread
            val milkTeas = milkTeaDao.getAll()

            // Update the UI on the main thread with the result
            withContext(Dispatchers.Main) {
                milkTeaAdapter.updateData(milkTeas)
                recyclerView.adapter = milkTeaAdapter
            }
        }

        return binding.root
    }
}



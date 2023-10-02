package com.example.meowtea

import LocalMilkTeaDataSource
import MilkTeaAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.databinding.FragmentStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root

        // Create an instance of LocalMilkTeaDataSource
        val dataSource = LocalMilkTeaDataSource(requireContext())

        // Use a coroutine to fetch milk tea data asynchronously
        GlobalScope.launch(Dispatchers.IO) {
            val milkTeaList = dataSource.getAllMilkTeas()

            // Update the UI on the main thread
            requireActivity().runOnUiThread {
                //RecyclerView to display the milk teas
                val recyclerView: RecyclerView = binding.recyclerView
                val adapter = MilkTeaAdapter(requireContext(), milkTeaList)
                // Replace with your data
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }

        return view
    }
}

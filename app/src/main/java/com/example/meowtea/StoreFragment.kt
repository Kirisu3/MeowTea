package com.example.meowtea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meowtea.database.AppDatabase
import com.example.meowtea.database.MilkTea
import com.example.meowtea.databinding.FragmentStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding
    private val milkTeas: List<MilkTea> = emptyList()

    private val milkTeaDatabase: AppDatabase by lazy {
        AppDatabase.create(requireContext())
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
        val milkTeaDao = milkTeaDatabase.milkTeaDao()

        val milkTeaAdapter = MilkTeaAdapter(milkTeas) { milkTea ->
            openDetailFragment(milkTea)
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val milkTeas = milkTeaDao.getAll()

            withContext(Dispatchers.Main) {
                milkTeaAdapter.updateData(milkTeas)
                recyclerView.adapter = milkTeaAdapter
            }
        }



        return binding.root
    }


    private fun openDetailFragment(milkTea: MilkTea) {
        val fragment = DetailFragment()
        val args = Bundle()
        args.putParcelable("milkTea", milkTea)
        fragment.arguments = args


        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }

}



package com.example.meowtea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        /* GlobalScope.launch(Dispatchers.Main) {
            val milkTeaDao = AppDatabase.getInstance(requireContext()).milkTeaDao()
            val milkTeaList: List<MilkTea> = withContext(Dispatchers.IO) {
                milkTeaDao.getAllMilkTeas()
            }

            val adapter = MilkTeaAdapter(milkTeaList)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        } */

        return view
    }
}

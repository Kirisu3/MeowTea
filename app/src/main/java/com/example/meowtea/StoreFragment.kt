package com.example.meowtea
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.databinding.FragmentStoreBinding


class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding
    lateinit var imgList: Array<Int>
    lateinit var productname: Array<String>
    lateinit var productdescription: Array<String>
    lateinit var productprice: Array<Int>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        imgList = arrayOf(
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
            R.drawable.noun_bubble_milk_tea_2216050,
        )
        productname = arrayOf(
            "Bogart1",
            "Bogart2",
            "Bogart3",
            "Bogart4",
            "Bogart5",
            "Bogart6",
            "Bogart7",
            "Bogart8",
            "Bogart9",
            "Bogart10",
        )

        productdescription = arrayOf(
            "descrip 1",
            "descrip 2",
            "descrip 3",
            "descrip 4",
            "descrip 6",
            "descrip 7",
            "descrip 8",
            "descrip 9",
            "descrip 10",
        )

        productprice = arrayOf(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10
        )




        return binding.root
    }

}
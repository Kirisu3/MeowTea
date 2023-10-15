package com.example.meowtea

import MilkTea
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)


        val milkTea = arguments?.getParcelable<MilkTea>("milkTea")
        if (milkTea != null) {
            textViewName.text = milkTea.name
        }


        return view
    }
}

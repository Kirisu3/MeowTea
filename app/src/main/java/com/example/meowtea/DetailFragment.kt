package com.example.meowtea

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.bumptech.glide.Glide
import com.example.meowtea.database.MilkTea

class DetailFragment : Fragment() {



    @SuppressLint("MissingInflatedId", "DiscouragedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val imageView = view.findViewById<ImageView>(R.id.detailImageView)
        val nameTextView = view.findViewById<TextView>(R.id.detailNameTextView)
        val btOrder = view.findViewById<Button>(R.id.Order)
        btOrder.setOnClickListener{

        }
        val milkTea = arguments?.getParcelable<MilkTea>("milkTea")


        if (milkTea != null) {
            nameTextView.text = milkTea.name

            // Load the image using a library like Glide or Picasso
            Glide.with(this)
                .load(resources.getIdentifier(milkTea.imagePath, "drawable", requireContext().packageName))
                .into(imageView)
        }

        return view
    }

}

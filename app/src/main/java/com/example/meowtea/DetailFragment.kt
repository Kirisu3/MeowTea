package com.example.meowtea

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        val milkTea = arguments?.getParcelable<MilkTea>("milkTea")


        if (milkTea != null) {
            nameTextView.text = milkTea.name
            Glide.with(this)
                .load(resources.getIdentifier(milkTea.imagePath, "drawable", requireContext().packageName))
                .into(imageView)
        }

        btOrder.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            val storeFragment = StoreFragment()

            val itemName = nameTextView.text.toString()
            val itemImageResId = resources.getIdentifier(milkTea?.imagePath, "drawable", requireContext().packageName)

            val existingCartFragment = parentFragmentManager.findFragmentByTag("cartFragment") as? CartFragment
            if (existingCartFragment != null) {
                existingCartFragment.addItemToCart(CartItem(itemName, itemImageResId))
            } else {
                val newCartFragment = CartFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.add(R.id.frame_layout, newCartFragment, "cartFragment")
                transaction.addToBackStack(null)
                transaction.commit()

                newCartFragment.addItemToCart(CartItem(itemName, itemImageResId))
            }

            Toast.makeText(requireContext(), "Item added to cart", Toast.LENGTH_SHORT).show()


            val enterAnim = R.anim.enter_from_right // Create this animation XML
            val exitAnim = R.anim.exit_to_left   // Create this animation XML

            val transaction = fragmentManager.beginTransaction()
            transaction.setCustomAnimations(enterAnim, exitAnim, enterAnim, exitAnim)

            transaction.replace(R.id.frame_layout, storeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }


}

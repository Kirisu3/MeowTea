import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.R

class MilkTeaAdapter(private val context: Context, private val data: List<MilkTea>) :
    RecyclerView.Adapter<MilkTeaAdapter.MilkTeaViewHolder>() {

    inner class MilkTeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define views in the ViewHolder
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilkTeaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.milk_tea_item, parent, false)
        return MilkTeaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MilkTeaViewHolder, position: Int) {
        val currentItem = data[position]

        // Bind data to the ViewHolder views
        holder.nameTextView.text = currentItem.name
        holder.descriptionTextView.text = currentItem.description
        holder.priceTextView.text = currentItem.price.toString()

        // Load and display the image using a library like Picasso or Glide
        Picasso.get().load(currentItem.imagePath).into(holder.imageView)
    }

    override fun getItemCount() = data.size
}
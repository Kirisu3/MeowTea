import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.R
import com.example.meowtea.databinding.FragmentStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root

        // Move these lines inside the view creation
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Use GlobalScope.launch for coroutines
        GlobalScope.launch(Dispatchers.Main) {
            val milkTeaList: List<MilkTea> = withContext(Dispatchers.IO) {
                val milkTeaDao = AppDatabase.getInstance(requireContext()).milkTeaDao()
                milkTeaDao.getAllMilkTeas()
            }

            val adapter = MilkTeaAdapter(milkTeaList)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        return view
    }
}

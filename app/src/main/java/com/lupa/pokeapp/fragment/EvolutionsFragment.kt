package com.lupa.pokeapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lupa.pokeapp.R
import com.lupa.pokeapp.adapter.EvolutionsAdapter
import com.lupa.pokeapp.model.Evolution

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EvolutionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EvolutionsFragment(
    private val evolutions: List<Evolution>
): Fragment() {

    private lateinit var evolutionsRecyclerView: RecyclerView
    private lateinit var adapter: EvolutionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_evolutions, container, false)
        evolutionsRecyclerView = view.findViewById(R.id.evolutionsRecyclerView)

        context?.let { context ->
            if (evolutions.isNullOrEmpty()) {
                evolutionsRecyclerView.visibility = View.GONE
                view.findViewById<View>(R.id.empty_state).visibility = View.VISIBLE
            } else {
                adapter = EvolutionsAdapter(context, evolutions)
                evolutionsRecyclerView.adapter = adapter
                evolutionsRecyclerView.setHasFixedSize(true)
                val layoutManager = LinearLayoutManager(context)
                evolutionsRecyclerView.layoutManager = layoutManager
                adapter.notifyDataSetChanged()
            }
        }
        return view
    }
}

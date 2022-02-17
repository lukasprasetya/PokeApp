package com.lupa.pokeapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import com.lupa.pokeapp.R
import com.lupa.pokeapp.model.Stat
import com.lupa.pokeapp.widget.StatWidget

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsFragment(
    private val stats: List<Stat>,
    @ColorRes private val color: Int
): Fragment() {

    private lateinit var statsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        statsContainer = view.findViewById(R.id.container)


        for (stat in stats) {
            val statWidget = StatWidget(view.context)
            statWidget.setStat(stat, color)
            statsContainer.addView(statWidget)
        }

        return view
    }
}
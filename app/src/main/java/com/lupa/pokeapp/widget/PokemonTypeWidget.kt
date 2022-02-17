package com.lupa.pokeapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.lupa.pokeapp.R

class PokemonTypeWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val typeTextView: TextView
    private val typeContainer: LinearLayout

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.layout_type_widget, this)
        typeTextView = findViewById(R.id.type_widget_name)
        typeContainer = findViewById(R.id.type_widget_container)
    }

    fun setType(type: String) {
        typeTextView.text = type
    }
}
package com.lupa.pokeapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lupa.pokeapp.DetailActivity
import com.lupa.pokeapp.R
import com.lupa.pokeapp.model.Pokemon
import com.lupa.pokeapp.utils.PokemonUtils
import com.lupa.pokeapp.utils.Utils

class PokemonAdapter(
    private val context: Context
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>(), Filterable {

    private val filter = PokemonListFilter(this)
    var dataset: List<Pokemon> = ArrayList()
        set(value) {
            field = value
            filteredDataset = field
        }

    var filteredDataset = dataset

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById<View>(R.id.cv_poke_row) as CardView
        val icon: ImageView = itemView.findViewById<View>(R.id.iv_poke_row) as ImageView
        val name: TextView = itemView.findViewById<View>(R.id.tv_poke_name) as TextView
        val id: TextView = itemView.findViewById<View>(R.id.tv_poke_id) as TextView
        val type1: TextView = itemView.findViewById<View>(R.id.tv_type1) as TextView
        val type2: TextView = itemView.findViewById<View>(R.id.tv_type2) as TextView
        val type2Container: LinearLayout =
            itemView.findViewById<View>(R.id.type2_container) as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_row_poke, parent, false
            )
        )


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = filteredDataset[position]
        holder.id.text = PokemonUtils.getIdTitle(pokemon.id)
        holder.name.text = pokemon.name
        holder.type1.text = pokemon.type1
        holder.type2.text = pokemon.type2
        holder.card.setCardBackgroundColor(ContextCompat.getColor(context, pokemon.cardColor))

        if (pokemon.type2.isEmpty()) {
            holder.type2Container.visibility = View.GONE
        } else {
            holder.type2Container.visibility = View.VISIBLE
        }

        Glide.with(context)
            .load(pokemon.imageUrl)
            .centerCrop()
            .placeholder(Utils.getLoadingPlaceholder(context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.icon)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", pokemon.id)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int = filteredDataset.size

    override fun getFilter(): Filter = filter
}
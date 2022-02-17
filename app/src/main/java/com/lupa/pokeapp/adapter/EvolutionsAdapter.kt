package com.lupa.pokeapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lupa.pokeapp.DetailActivity
import com.lupa.pokeapp.R
import com.lupa.pokeapp.model.Evolution
import com.lupa.pokeapp.utils.PokemonUtils
import com.lupa.pokeapp.utils.Utils

class EvolutionsAdapter(
    private val context: Context,
    private val dataset: List<Evolution>
) : RecyclerView.Adapter<EvolutionsAdapter.EvolutionViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): EvolutionViewHolder =
        EvolutionViewHolder(
            LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_row_evolution, viewGroup, false))

    override fun onBindViewHolder(viewHolder: EvolutionViewHolder, pos: Int) {
        val evolution = dataset[pos]

        if (pos == 0) {
            viewHolder.itemView.setPadding(0, 0, 0, 0)
        }

        viewHolder.originNameTextView.text = evolution.originName
        viewHolder.evolutionNameTextView.text = evolution.evolutionName
        viewHolder.levelTextView.text = evolution.condition

        Glide.with(context)
            .load(PokemonUtils.getImageUrl(evolution.originId))
            .centerCrop()
            .placeholder(Utils.getLoadingPlaceholder(context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.originImageView)

        Glide.with(context)
            .load(PokemonUtils.getImageUrl(evolution.evolutionId))
            .centerCrop()
            .placeholder(Utils.getLoadingPlaceholder(context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.evolutionImageView)

        viewHolder.originImageView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", evolution.originId)
            context.startActivity(intent)
        }

        viewHolder.evolutionImageView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", evolution.evolutionId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataset.size

    class EvolutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val originImageView: ImageView = itemView.findViewById<View>(R.id.originImage) as ImageView
        val originNameTextView: TextView = itemView.findViewById<View>(R.id.originTextView) as TextView
        val evolutionImageView: ImageView = itemView.findViewById<View>(R.id.evolutionImage) as ImageView
        val evolutionNameTextView: TextView = itemView.findViewById<View>(R.id.evolutionTextView) as TextView
        val levelTextView: TextView = itemView.findViewById<View>(R.id.evolutionLvl) as TextView
    }
}
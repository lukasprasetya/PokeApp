package com.lupa.pokeapp

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.tabs.TabLayoutMediator
import com.lupa.pokeapp.adapter.PokemonPagerAdapter
import com.lupa.pokeapp.databinding.ActivityDetailBinding
import com.lupa.pokeapp.model.PokemonDetail
import com.lupa.pokeapp.model.State
import com.lupa.pokeapp.utils.PokemonUtils
import com.lupa.pokeapp.utils.Utils
import com.lupa.pokeapp.viewmodel.PokemonDetailViewModel
import com.lupa.pokeapp.widget.PokemonTypeWidget
import dagger.hilt.android.AndroidEntryPoint


/*
@AndroidEntryPoint
class DetailActivity : ViewActivity() {

    private val pokemonDetailViewModel by viewModels<PokemonDetailViewModel>()
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private var pagerAdapter: PokemonPagerAdapter? = null
    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        pokemonDetailViewModel.getViewStateLiveData()
            .observe(this, { updateViewStatus(it) })

        id = intent.getStringExtra("id").toString()
        pokemonDetailViewModel.fetchPokemon(id)

    }
    private fun updateViewStatus(networkState: State<PokemonDetail?>) {
        when (networkState) {
            is State.Loading -> showLoadingView()
            is State.Success -> showPokemonData(networkState.data)
            is State.Error -> showErrorView(networkState.message)
        }
    }

    private fun showPokemonData(pokemon: PokemonDetail?) {
        pokemon?.let {
            Glide.with(this)
                .load(pokemon.imageUrl)
                .centerCrop()
                .placeholder(Utils.getLoadingPlaceholder(this) as Drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)

            binding.name.text = it.name.replaceFirstChar { c -> c.uppercase() }
            binding.id.text = PokemonUtils.getIdTitle(it.id)

            binding.desc.text = pokemon.desc

            binding.detailContainer.background = ContextCompat.getDrawable(this, pokemon.color)
            setUpActionbar(pokemon.color)

            for (type in pokemon.types) {
                val pokemonTypeWidget = PokemonTypeWidget(this)
                pokemonTypeWidget.setType(type)
                binding.typesContainer.addView(pokemonTypeWidget)
            }

            pagerAdapter = PokemonPagerAdapter(this, pokemon)
            binding.pager.adapter = pagerAdapter

            binding.tabLayout.setTabTextColors(
                ContextCompat.getColor(applicationContext, pokemon.color), Color.WHITE)
            binding.tabLayout.setSelectedTabIndicatorColor(
                ContextCompat.getColor(applicationContext, pokemon.color))

            val tableLayoutMediator = TabLayoutMediator(binding.tabLayout, pager) { tab, pos ->
                when (pos) {
                    0 -> {
                        tab.text = "     Stats     "
                    }
                    1 -> {
                        tab.text = " Evolutions "
                    }
                }
            }
            tableLayoutMediator.attach()
        }
        showLayoutView()
    }

    private fun setUpActionbar(color: Int) {
        supportActionBar?.apply {
            show()
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
            setDisplayHomeAsUpEnabled(true)
            setBackgroundDrawable(ContextCompat.getDrawable(applicationContext, color))
            elevation = 0f
            title = ""
        }
        Utils.changeStatusBarColor(this, color)
    }
    override fun getLayoutView(): View = binding.root

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onRetry() {
        pokemonDetailViewModel.fetchPokemon(id)
    }
}*/

@AndroidEntryPoint
class PokemonDetailActivity : ViewActivity() {

    private val pokemonDetailViewModel by viewModels<PokemonDetailViewModel>()
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private var pagerAdapter: PokemonPagerAdapter? = null
    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        pokemonDetailViewModel.getViewStateLiveData()
            .observe(this, { updateViewStatus(it) })

        id = intent.getStringExtra("id").toString()
        pokemonDetailViewModel.fetchPokemon(id)
    }

    override fun getLayoutView(): View = binding.root

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRetry() {
        pokemonDetailViewModel.fetchPokemon(id)
    }

    private fun updateViewStatus(networkState: State<PokemonDetail?>) {
        when (networkState) {
            is State.Loading -> showLoadingView()
            is State.Success -> showPokemonData(networkState.data)
            is State.Error -> showErrorView(networkState.message)
        }
    }

    private fun showPokemonData(pokemon: PokemonDetail?) {
        pokemon?.let {
            Glide.with(this)
                .load(pokemon.imageUrl)
                .centerCrop()
                .placeholder(Utils.getLoadingPlaceholder(this) as Drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)

            binding.name.text = it.name.replaceFirstChar { c -> c.uppercase() }
            binding.id.text = PokemonUtils.getIdTitle(it.id)

            binding.desc.text = pokemon.desc

            binding.detailContainer.background = ContextCompat.getDrawable(this, pokemon.color)
            setUpActionbar(pokemon.color)

            for (type in pokemon.types) {
                val pokemonTypeWidget = PokemonTypeWidget(this)
                pokemonTypeWidget.setType(type)
                binding.typesContainer.addView(pokemonTypeWidget)
            }

            pagerAdapter = PokemonPagerAdapter(this, pokemon)
            binding.viewPager.adapter = pagerAdapter

            binding.tabLayout.setTabTextColors(
                ContextCompat.getColor(applicationContext, pokemon.color), Color.WHITE
            )
            binding.tabLayout.setSelectedTabIndicatorColor(
                ContextCompat.getColor(applicationContext, pokemon.color)
            )

            val tableLayoutMediator = TabLayoutMediator(binding.tabLayout, viewPager) { tab, pos ->
                when (pos) {
                    0 -> {
                        tab.text = "     Stats     "
                    }
                    else -> {
                        tab.text = " Evolutions "
                    }
                }
            }
            tableLayoutMediator.attach()
        }
        showLayoutView()
    }

    private fun setUpActionbar(color: Int) {
        supportActionBar?.apply {
            show()
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
            setDisplayHomeAsUpEnabled(true)
            setBackgroundDrawable(ContextCompat.getDrawable(applicationContext, color))
            elevation = 0f
            title = ""
        }
        Utils.changeStatusBarColor(this, color)
    }
}
package com.lupa.pokeapp

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lupa.pokeapp.adapter.PokemonAdapter
import com.lupa.pokeapp.databinding.ActivityMainBinding
import com.lupa.pokeapp.model.Pokemon
import com.lupa.pokeapp.model.State
import com.lupa.pokeapp.viewmodel.PokemonViewModel

class MainActivity : ViewActivity(){

    private val pokeListViewModel by viewModels<PokemonViewModel>()
    private var pokeListAdapter: PokemonAdapter = PokemonAdapter(this)
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvMain.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvMain.layoutManager = layoutManager
        binding.rvMain.adapter = pokeListAdapter
        setListAnimation()

        pokeListViewModel.getViewStateLiveData()
            .observe(this, { updateViewStatus(it) })

        binding.srlPoke.setOnRefreshListener {
            pokeListViewModel.fetchPokemons()
        }
        pokeListViewModel.fetchPokemons()
    }

    override fun getLayoutView() : View = binding.root

    override fun onRetry() {
        pokeListViewModel.fetchPokemons()
    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pokeapp_options_menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search by name or id"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                pokeListAdapter.filter.filter(newText)
                return false
            }
        })
        return true
    }*/

    private fun updateViewStatus(networkState: State<List<Pokemon>?>) {
        when (networkState) {
            is State.Loading -> showLoadingView()
            is State.Success -> loadList(networkState.data)
            is State.Error -> showErrorView(networkState.message)
        }
    }

    private fun loadList(list: List<Pokemon>?) {
        list?.let {
            pokeListAdapter.dataset = it
            pokeListAdapter.notifyDataSetChanged()
        }
        showLayoutView()
        binding.srlPoke.isRefreshing = false
    }

    private fun setListAnimation() {
        val resId = R.anim.slide_from_bottom
        val animation = AnimationUtils.loadLayoutAnimation(this, resId)
        binding.rvMain.layoutAnimation = animation
    }
}

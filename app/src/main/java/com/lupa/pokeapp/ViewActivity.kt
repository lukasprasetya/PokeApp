package com.lupa.pokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lupa.pokeapp.databinding.ActivityViewBinding

abstract class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutView.addView(getLayoutView())

        binding.errorView.btnRetryError.setOnClickListener { onRetry() }
    }

    protected abstract fun getLayoutView(): View

    protected abstract fun onRetry()

    protected fun showLayoutView() {
        binding.errorView.root.visibility = View.GONE
        binding.CPILoadingview.visibility = View.GONE
        binding.layoutView.visibility = View.VISIBLE
    }

    protected fun showErrorView(error: String) {
        binding.CPILoadingview.visibility = View.GONE
        binding.layoutView.visibility = View.GONE
        binding.errorView.tvPesanError.text = error
        binding.errorView.root.visibility = View.VISIBLE
    }

    protected fun showLoadingView() {
        binding.errorView.root.visibility = View.GONE
        binding.layoutView.visibility = View.GONE
        binding.CPILoadingview.visibility = View.VISIBLE
    }
}
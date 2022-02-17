package com.lupa.pokeapp.utils

object StringUtils {
    fun getIdFromUrl(url: String): String {
        val splitUrl = url.split('/')
        return splitUrl[splitUrl.size - 2]
    }
}
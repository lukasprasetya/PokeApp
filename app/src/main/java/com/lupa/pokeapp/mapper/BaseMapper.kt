package com.lupa.pokeapp.mapper

interface BaseMapper <in A, out B> {

    fun map(type: A): B
}
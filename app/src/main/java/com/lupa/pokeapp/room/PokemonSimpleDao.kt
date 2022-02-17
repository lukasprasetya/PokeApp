package com.lupa.pokeapp.room

import androidx.room.*

@Dao
interface PokemonSimpleDao {

    @Query("SELECT * FROM PokemonSimpleEntity")
    suspend fun getAll(): List<PokemonSimpleEntity>

    @Query("SELECT * FROM PokemonSimpleEntity WHERE id = :id")
    suspend fun getById(id: String): PokemonSimpleEntity

    @Update
    suspend fun update(pokemonSimpleEntity: PokemonSimpleEntity)

    @Insert
    suspend fun insert(list: List<PokemonSimpleEntity>)

    @Delete
    suspend fun delete(pokemonSimpleEntity: PokemonSimpleEntity)
}
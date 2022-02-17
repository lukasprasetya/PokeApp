package com.lupa.pokeapp.room

import androidx.room.*

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun getById(id: String): PokemonEntity?

    @Update
    suspend fun update(pokemonEntity: PokemonEntity)

    @Insert
    suspend fun insert(list: PokemonEntity)

    @Delete
    suspend fun delete(pokemonEntity: PokemonEntity)
}
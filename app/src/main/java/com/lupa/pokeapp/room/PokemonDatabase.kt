package com.lupa.pokeapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PokemonSimpleEntity::class, PokemonEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonSimpleDao(): PokemonSimpleDao

    abstract fun pokemonDao(): PokemonDao
}
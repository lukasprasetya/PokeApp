package com.lupa.pokeapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lupa.pokeapp.model.EvolutionChain
import com.lupa.pokeapp.model.Stat
import java.lang.reflect.Type

object Converters {

    @TypeConverter
    fun stringListToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun statListToJson(list: List<Stat>?): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Stat?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun jsonToStatList(json: String?): List<Stat> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Stat?>?>() {}.type
        return gson.fromJson<List<Stat>>(json, type)
    }

    @TypeConverter
    fun evolutionChainToJson(evolutionChain: EvolutionChain): String {
        val gson = Gson()
        val type: Type = object : TypeToken<EvolutionChain?>() {}.type
        return gson.toJson(evolutionChain, type)
    }

    @TypeConverter
    fun jsonToEvolutionChain(json: String?): EvolutionChain {
        val gson = Gson()
        val type: Type = object : TypeToken<EvolutionChain?>() {}.type
        return gson.fromJson<EvolutionChain>(json, type)
    }
}
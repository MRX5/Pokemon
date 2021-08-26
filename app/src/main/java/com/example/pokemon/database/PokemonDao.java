package com.example.pokemon.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemon.model.Pokemon;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface PokemonDao {

    @Insert
    public Completable insertPokemon(Pokemon pokemon);

    @Query("delete from fav_table where name=:pokemonName")
    public Completable deletePokemon(String pokemonName);

    @Query("select * from fav_table")
    public Observable<List<Pokemon>> getFavPokemons();


}

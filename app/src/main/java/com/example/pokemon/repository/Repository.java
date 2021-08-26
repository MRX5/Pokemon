package com.example.pokemon.repository;
import com.example.pokemon.database.PokemonDao;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.PokemonResponse;
import com.example.pokemon.network.ApiService;

import java.util.List;
    
import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private ApiService apiService;
    private PokemonDao pokemonDao;

    @Inject
    public Repository(ApiService apiService, PokemonDao pokemonDao) {
        this.apiService = apiService;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonResponse> getPokemons() {
        return apiService.getPokemons();
    }

    public Completable insertPokemon(Pokemon pokemon) {
        return pokemonDao.insertPokemon(pokemon);
    }

    public Completable deletePokemon(String pokemonName){
        return pokemonDao.deletePokemon(pokemonName);
    }

    public Observable<List<Pokemon>> getFavPokemons(){
        return pokemonDao.getFavPokemons();
    }
}

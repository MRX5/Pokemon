package com.example.pokemon.viewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.PokemonResponse;
import com.example.pokemon.repository.Repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private static final String TAG = "PokemonViewModel";
    private Repository repository;

    private MutableLiveData<List<Pokemon>> pokemonList = new MutableLiveData<>();
    private MutableLiveData<List<Pokemon>> favPokemonList = new MutableLiveData<>();

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public MutableLiveData<List<Pokemon>> getFavPokemonList() {
        return favPokemonList;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, List<Pokemon>>() {
                    @Override
                    public List<Pokemon> apply(@NotNull PokemonResponse pokemonResponse) throws Exception {
                        List<Pokemon> pokemons = pokemonResponse.getResults();
                        for (Pokemon pokeomn : pokemons) {
                            String url = pokeomn.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokeomn.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return pokemons;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> pokemonList.setValue(results),
                        error -> Log.e(TAG, error.getMessage()));
    }

    public void insertPokemon(Pokemon pokemon) {
        repository.insertPokemon(pokemon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deletePokemon(String name) {
        repository.deletePokemon(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void getFavPokemons() {
        repository.getFavPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemons -> favPokemonList.setValue(pokemons), error -> Log.d(TAG, "Failed to get pokemons"));
    }
}

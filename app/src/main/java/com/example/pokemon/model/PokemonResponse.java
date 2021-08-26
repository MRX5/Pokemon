package com.example.pokemon.model;

import java.util.ArrayList;
import java.util.List;

public class PokemonResponse {

    private int count;
    private String next,previous;
    private ArrayList<Pokemon> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Pokemon> getResults() {
        return results;
    }
}

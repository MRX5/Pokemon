package com.example.pokemon.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.pokemon.R;
import com.example.pokemon.model.Pokemon;
import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private List<Pokemon> mItems = new ArrayList<>();
    private Context mContext;

    public PokemonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Log.d("adapter", mItems.get(position).getName());
        holder.pokemonName.setText(mItems.get(position).getName());
        Glide.with(mContext).load(mItems.get(position).getUrl()).into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<Pokemon> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }
    public Pokemon getPokemonAt(int position){
        return mItems.get(position);
    }
    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemonImage;
        private TextView pokemonName;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage=itemView.findViewById(R.id.pokemon_imageView);
            pokemonName=itemView.findViewById(R.id.pokemon_name_textView);
        }
    }
}

package com.example.pokemon.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.pokemon.database.PokemonDB;
import com.example.pokemon.database.PokemonDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class dbModule {

        @Provides
        @Singleton
        public static PokemonDB provideDB(@ApplicationContext Context context){
            return Room.databaseBuilder(context,PokemonDB.class,"fav_DB")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        @Provides
        @Singleton
        public static PokemonDao provideDao(PokemonDB pokemonDB){
            return pokemonDB.pokemonDao();
        }
}

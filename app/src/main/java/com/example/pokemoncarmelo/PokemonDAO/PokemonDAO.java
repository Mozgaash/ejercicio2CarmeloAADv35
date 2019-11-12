package com.example.pokemoncarmelo.PokemonDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pokemoncarmelo.Entity.Pokemon;

import java.util.List;

@Dao
public interface PokemonDAO {

    @Delete
    void delete(Pokemon pokemon);

    @Update
    void edit(Pokemon pokemon);

    @Query("SELECT * FROM pokemon")
    List<Pokemon> getAll();

    @Insert
    long insert(Pokemon pokemon);

    @Query("select * from pokemon")
    LiveData<List<Pokemon>> getAllLive();

    @Query("select * from pokemon where id > :pkid")
    LiveData<Pokemon> selectOne(long pkid);

}

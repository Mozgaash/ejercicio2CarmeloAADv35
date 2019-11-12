package com.example.pokemoncarmelo;

import android.content.Intent;
import android.os.Bundle;

import com.example.pokemoncarmelo.Entity.Pokemon;
import com.example.pokemoncarmelo.View.MainViewModel;
import com.example.pokemoncarmelo.View.PokemonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecycler;
    //private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager layoutManager;
    private MainViewModel viewModel;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        initComponents();





        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertaNuevo();
            }
        });
    }

    private void insertaNuevo() {
        Intent i = new Intent(this, InsertarActivity.class);
        startActivity(i);
    }

    private void initComponents() {

        myRecycler = findViewById(R.id.rvPokemons);
        layoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutManager);

        pokemonAdapter = new PokemonAdapter(this, new PokemonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon) {
                Intent i = new Intent(MainActivity.this, EditBorrarActivity.class);
                i.putExtra("pokemonID", pokemon);
                startActivity(i);
            }
        });
        myRecycler.setAdapter(pokemonAdapter);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                pokemonAdapter.setMisPokemons(pokemons);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

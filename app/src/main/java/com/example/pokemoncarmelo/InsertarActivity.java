package com.example.pokemoncarmelo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokemoncarmelo.Entity.Pokemon;
import com.example.pokemoncarmelo.View.MainViewModel;

public class InsertarActivity extends AppCompatActivity {

    boolean imageSelected = false;
    String imagen;
    private static final int PHOTO_SELECTED = 1;
    EditText etName;
    ImageView ivSelected;
    Spinner spinnerTipos;
    Button btInsertar, btSeleccionar;
    Pokemon pokemon;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        Intent intent = getIntent();
        initComponents();
    }

    private void initComponents() {
        etName = findViewById(R.id.etNombre);
        spinnerTipos = findViewById(R.id.spinnerTipo);
        ivSelected = findViewById(R.id.imgSeleccionada);
        btInsertar = findViewById(R.id.btInsertar);
        btSeleccionar = findViewById(R.id.btSeleccionar);

        pokemon = new Pokemon();

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initEvents();
    }

    private void initEvents() {
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageSelected == true) {
                    insertarPokemon();

                }else {
                    Toast.makeText(InsertarActivity.this, R.string.ErrorSeleccionarFoto, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });

    }

    private void insertarPokemon() {

        String name, type;

        name = etName.getText().toString();
        type = spinnerTipos.getSelectedItem().toString();

        pokemon.setName(name);
        pokemon.setType(type);
        pokemon.setImageID(imagen);

        viewModel.insert(pokemon);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_SELECTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SELECTED && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri imageUri = data.getData();


            Glide.with(this)
                    .load(imageUri)
                    .override(500, 500)// prueba de escalado
                    .into(ivSelected);
            imagen = imageUri.toString();
            imageSelected = true;
        }
    }

}

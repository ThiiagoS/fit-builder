package com.example.fitbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_ListaDeAmigos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_de_amigos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listaDeAmigos_ListaDeAmigos), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnVoltar = findViewById(R.id.btnvoltar_ListaDeAmigos);
        Button btnAdicionarAmigo = findViewById(R.id.buttonAdicionarAmigo_ListaDeAmigos);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicionarAmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_ListaDeAmigos.this, Activity_AdicionarAmigo.class);
                startActivity(intent);
            }
        });

    }
}

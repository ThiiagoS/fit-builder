package com.example.fitbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_ListaDeExercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_de_exercicios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_lista_de_exercicios), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnVoltar = findViewById(R.id.btnvoltar_ListaDeExercicios);
        Button btnEditar = findViewById(R.id.buttonEditar_ListaDeExercicios);
        Button btnTreinar = findViewById(R.id.buttonTreinar_ListaDeExercicios);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_ListaDeExercicios.this, Activity_EditarListaDeExercicios.class);
                startActivity(intent);
            }
        });

        btnTreinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_ListaDeExercicios.this, Activity_Exercicio.class);
                startActivity(intent);
            }
        });
    }
}
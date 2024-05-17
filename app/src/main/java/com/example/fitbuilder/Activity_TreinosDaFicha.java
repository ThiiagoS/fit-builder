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

public class Activity_TreinosDaFicha extends AppCompatActivity {

    ImageView btnVoltar;
    Button btnFicha1;
    Button btnFicha2;
    Button btnFicha3;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_treinos_da_ficha);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.treinos_da_ficha_treinosDaFicha), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVoltar = findViewById(R.id.treinos_da_ficha_btnvoltar);
        btnFicha1 = findViewById(R.id.treinos_da_ficha_buttonFicha1);
        btnFicha2 = findViewById(R.id.treinos_da_ficha_buttonFicha2);
        btnFicha3 = findViewById(R.id.treinos_da_ficha_buttonFicha3);
        btnAdicionar = findViewById(R.id.treinos_da_ficha_buttonAdicionarTreino);

        btnFicha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_TreinosDaFicha.this, Activity_ListaDeExercicios.class);
                startActivity(intent);
            }
        });

        btnFicha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_TreinosDaFicha.this, Activity_ListaDeExercicios.class);
                startActivity(intent);
            }
        });

        btnFicha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_TreinosDaFicha.this, Activity_ListaDeExercicios.class);
                startActivity(intent);
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_TreinosDaFicha.this, Activity_CadastroTreino.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}


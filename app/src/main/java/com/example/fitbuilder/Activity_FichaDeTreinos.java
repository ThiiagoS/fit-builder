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

public class Activity_FichaDeTreinos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ficha_de_treinos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fichaDeTreinos_FichaDeTreinos), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnVoltar = findViewById(R.id.btnvoltar_FichaDeTreinos);
        Button btnFicha1 = findViewById(R.id.buttonFicha1_FichaDeTreinos);
        Button btnFicha2 = findViewById(R.id.buttonFicha2_FichaDeTreinos);
        Button btnFicha3 = findViewById(R.id.buttonFicha3_FichaDeTreinos);
        Button btnFicha4= findViewById(R.id.buttonFicha4_FichaDeTreinos);
        Button btnFicha5 = findViewById(R.id.buttonFicha5_FichaDeTreinos);;
        Button btnAdicionarFicha= findViewById(R.id.buttonAdicionarFicha_FichaDeTreinos);
        Button btnRemoverFicha = findViewById(R.id.buttonRemoverFicha_FichaDeTreinos);;

        btnFicha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_FichaDeTreinos.this, Activity_TreinosDaFicha.class);
                startActivity(intent);
            }
        });

        btnFicha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_FichaDeTreinos.this, Activity_TreinosDaFicha.class);
                startActivity(intent);
            }
        });

        btnFicha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_FichaDeTreinos.this, Activity_TreinosDaFicha.class);
                startActivity(intent);
            }
        });

        btnFicha4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_FichaDeTreinos.this, Activity_TreinosDaFicha.class);
                startActivity(intent);
            }
        });

        btnFicha5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_FichaDeTreinos.this, Activity_TreinosDaFicha.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicionarFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_FichaDeTreinos.this, Activity_CadastroFicha.class);
                startActivity(intent);
            }
        });

        btnRemoverFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Activity_FichaDeTreinos.this, Activity_TreinosDaFicha.class);
                //startActivity(intent);
            }
        });
    }
}

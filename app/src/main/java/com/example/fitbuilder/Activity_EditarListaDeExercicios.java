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

public class Activity_EditarListaDeExercicios extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_lista_de_exercicios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edtarListaDeExercicios_EditarListaDeExercicios), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnVoltar = findViewById(R.id.btnvoltar_EditarListaDeExercicios);
        Button btnAdicionarExercicio = findViewById(R.id.buttonAdicionarExercicio_EditarListaDeExercicios);
        Button btnRemoverExercicio = findViewById(R.id.buttonRemoverExercicio_EditarListaDeExercicios);

        btnAdicionarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_EditarListaDeExercicios.this, Activity_CadastroExercicio.class);
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

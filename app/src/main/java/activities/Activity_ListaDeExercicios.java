package activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import java.util.ArrayList;
import java.util.Objects;

import cruds.Exercise;
import cruds.Training;

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

        Intent intent = getIntent();
        String idTreino = intent.getStringExtra("ID_TRAINING");

        Exercise exercise = new Exercise(this);

        LinearLayout buttonContainer = findViewById(R.id.button_container);

        ArrayList<String> buttonNames = new ArrayList<>();
        ArrayList<Integer> buttonIds = new ArrayList<>();

        Cursor cursor = exercise.getAllExercises();
        if (cursor.moveToFirst()) {
            do {
                int indexNome = cursor.getColumnIndex("name");
                int indexTrainingID = cursor.getColumnIndex("training_id");
                int indexId = cursor.getColumnIndex("id");

                String idTraining = cursor.getString(indexTrainingID);
                String nomeTraining = cursor.getString(indexNome);
                String idExercise = cursor.getString(indexId);

                if (Objects.equals(idTraining, idTreino)) {
                    buttonNames.add(nomeTraining);
                    buttonIds.add(Integer.parseInt(idExercise));
                }

            } while (cursor.moveToNext());
        }

        Integer index = 0;
        for (String name : buttonNames) {
            AppCompatButton button = new AppCompatButton(this);
            button.setText(name);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 10, 0, 10);
            button.setLayoutParams(params);

            buttonContainer.addView(button);

            Integer finalIndex = index;
            button.setOnClickListener(v ->
                    {

                    }
            );
            index++;
        }

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
                intent.putExtra("ID_TRAINING", idTreino);
                startActivity(intent);
            }
        });

        btnTreinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String name : buttonNames) {
                    Intent intent = new Intent(Activity_ListaDeExercicios.this, Activity_Exercicio.class);
                    startActivity(intent);
                }
            }
        });
    }
}
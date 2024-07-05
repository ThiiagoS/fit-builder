package activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import java.util.ArrayList;
import java.util.Objects;

import cruds.Exercise;

public class Activity_ListaDeExercicios extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityLauncher;

    private int currentIndex = 0;
    private int totalActivities = 0;

    private ArrayList<Integer> buttonIds = new ArrayList<>();
    private  ArrayList<String> buttonNames = new ArrayList<>();
    private  ArrayList<String> buttonSeries = new ArrayList<>();
    private  ArrayList<String> buttonRepetitions = new ArrayList<>();

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

        TextView titleTraining = findViewById(R.id.textViewTitle_ListaDeExercicios);


        Intent intent = getIntent();
        String idTreino = intent.getStringExtra("ID_TRAINING");
        String nomeTreino = intent.getStringExtra("NAME_TRAINING");


        titleTraining.setText("Treino " + nomeTreino);

        Exercise exercise = new Exercise(this);

        LinearLayout buttonContainer = findViewById(R.id.button_container);

        Cursor cursor = exercise.getAllExercises();
        if (cursor.moveToFirst()) {
            do {
                int indexTrainingID = cursor.getColumnIndex("training_id");
                int indexId = cursor.getColumnIndex("id");
                int indexNome = cursor.getColumnIndex("name");
                int indexSeries = cursor.getColumnIndex("series");
                int indexRepetition = cursor.getColumnIndex("repetition");

                String nomeTraining = cursor.getString(indexNome);
                String idTraining = cursor.getString(indexTrainingID);
                String idExercise = cursor.getString(indexId);
                String idSeries = cursor.getString(indexSeries);
                String idRepetition = cursor.getString(indexRepetition);

                if (Objects.equals(idTraining, idTreino)) {
                    buttonIds.add(Integer.parseInt(idExercise));
                    buttonNames.add(nomeTraining);
                    buttonSeries.add(idSeries);
                    buttonRepetitions.add(idRepetition);
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
            button.setOnClickListener(v -> {
            });
            index++;
        }

        totalActivities = buttonNames.size();

        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    boolean shouldStop = data.getBooleanExtra("should_stop", false);
                    if (shouldStop) {
                        currentIndex = 0;
                        return;
                    }
                    if (currentIndex < totalActivities) {
                        startNextActivity();
                    }
                }
            }
        });

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
                intent.putExtra("NAME_TRAINING", nomeTreino);
                intent.putExtra("ID_TRAINING", idTreino);
                startActivity(intent);
                finish();
            }
        });

        btnTreinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex= 0;
                startNextActivity();
            }
        });

    }

    private void startNextActivity() {
        if (currentIndex < totalActivities) {
            Intent intent = new Intent(Activity_ListaDeExercicios.this, Activity_Exercicio.class);
            intent.putExtra("NAME_EXERCISE", buttonNames.get(currentIndex));
            intent.putExtra("SERIES_EXERCISE", buttonSeries.get(currentIndex));
            intent.putExtra("REPETITION_EXERCISE", buttonRepetitions.get(currentIndex));
            activityLauncher.launch(intent);
            currentIndex++;
        }
    }

}
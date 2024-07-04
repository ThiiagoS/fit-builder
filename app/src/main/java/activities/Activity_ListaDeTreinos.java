package activities;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import java.util.ArrayList;
import java.util.Objects;

import cruds.Training;

public class Activity_ListaDeTreinos extends AppCompatActivity {

    ImageView btnVoltar;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_de_treinos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.treinos_da_ficha_treinosDaFicha), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVoltar = findViewById(R.id.treinos_da_ficha_btnvoltar);
        btnAdicionar = findViewById(R.id.treinos_da_ficha_buttonAdicionarTreino);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        Training training = new Training(this);

        LinearLayout buttonContainer = findViewById(R.id.button_container);

        ArrayList<String> buttonNames = new ArrayList<>();
        ArrayList<Integer> buttonIds = new ArrayList<>();

        Cursor cursor = training.getAllTrainings();
        if (cursor.moveToFirst()) {
            do {
                int indexNome = cursor.getColumnIndex("name");
                int indexUserId = cursor.getColumnIndex("user_id");
                int indexId = cursor.getColumnIndex("id");

                String idUser = cursor.getString(indexUserId);
                String nomeTraining = cursor.getString(indexNome);
                String idTraining = cursor.getString(indexId);

                if (Objects.equals(idUser, id)) {
                    buttonNames.add(nomeTraining);
                    buttonIds.add(Integer.parseInt(idTraining));
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
                        Intent intent2 = new Intent(Activity_ListaDeTreinos.this, Activity_ListaDeExercicios.class);
                        intent2.putExtra("NAME_TRAINING", buttonNames.get(finalIndex));
                        intent2.putExtra("ID_TRAINING", buttonIds.get(finalIndex).toString());
                        startActivity(intent2);
                    }
            );
            index++;
        }

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_ListaDeTreinos.this, Activity_CadastroTreino.class);
                intent.putExtra("ID", id);
                startActivity(intent);
                finish();
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


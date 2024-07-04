package activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import java.util.Objects;

import cruds.Friends;
import cruds.Training;
import cruds.User;
import utils.Dialog;

public class Activity_CadastroTreino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_treino);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cadastro_treino), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnVoltar = findViewById(R.id.btnvoltar_CadastroTreino);
        Button btnAdicionar = findViewById(R.id.editAdicionarFicha_CadastroTreino);

        Intent intent = getIntent();
        String idUser = intent.getStringExtra("ID");

        Training training = new Training(this);
        User user = new User(this);

        EditText nomeTreino = findViewById(R.id.editNomeExercicio_CadastroTreino);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeDoTreino = nomeTreino.getText().toString();

                Cursor cursorTreino = training.getAllTrainings();
                if (cursorTreino.moveToFirst()) {
                    do {
                        int indexNome = cursorTreino.getColumnIndex("name");
                        int indexUserID = cursorTreino.getColumnIndex("user_id");

                        String nomeTreinoCadastrado = cursorTreino.getString(indexNome);
                        String idUserTreinoCadastrado = cursorTreino.getString(indexUserID);

                        if (Objects.equals(nomeDoTreino, nomeTreinoCadastrado) && Objects.equals(idUserTreinoCadastrado, idUser) ) {
                            Dialog.showErrorDialog(Activity_CadastroTreino.this, "Erro", "Este treino ja foi adicionado.");
                            return;
                        }

                    } while (cursorTreino.moveToNext());
                }

                training.addTraining(nomeDoTreino,Integer.parseInt(idUser));
                finish();
            }
        });


    }
}
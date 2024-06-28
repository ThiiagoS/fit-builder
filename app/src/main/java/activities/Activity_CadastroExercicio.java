package activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import cruds.Exercise;

public class Activity_CadastroExercicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_exercicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cadastro_exercicio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnVoltar = findViewById(R.id.btnvoltar_Exercicio);
        Button btnAdicionar = findViewById(R.id.editAdicionarFicha_CadastroExercicio);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText NomeExercico = findViewById(R.id.editNomeExercicio_CadastroExercicio);
                    EditText MusculoTrabalhado = findViewById(R.id.MusculoTrabalhado_CadastroExercicio);
                    EditText Series = findViewById(R.id.editSeries_CadastroExercicio);
                    EditText Repeticoes = findViewById(R.id.editRepeticoes_CadastroExercicio);
                    EditText TempoDescanco = findViewById(R.id.editTempoDescanco_CadastroExercicio);

                    String value;

                    String nome = NomeExercico.getText().toString();
                    String musculo = MusculoTrabalhado.getText().toString();
                    value = Series.getText().toString();
                    int series = Integer.parseInt(value);
                    value = Repeticoes.getText().toString();
                    int repeticoes = Integer.parseInt(value);
                    value = TempoDescanco.getText().toString();
                    int tempoDescanco = Integer.parseInt(value);

                    //exercise.addExercise(nome, musculo, series, repeticoes, tempoDescanco);

                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
}
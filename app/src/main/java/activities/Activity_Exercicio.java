package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

public class Activity_Exercicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_exercicio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnVoltar = findViewById(R.id.btnvoltar_Exercicio);
        Button btnRelealizado_Exercicio = findViewById(R.id.editRelealizado_Exercicio);

        TextView titleExercise = findViewById(R.id.txtTitulo_Exercicio);

        Intent intent = getIntent();
        String nameExercise = intent.getStringExtra("NAME_EXERCISE");

        titleExercise.setText(nameExercise);

        btnRelealizado_Exercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("should_stop", false);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("should_stop", true);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }

}

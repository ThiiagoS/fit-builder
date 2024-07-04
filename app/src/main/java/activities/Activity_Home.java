package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

public class Activity_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        String nome = intent.getStringExtra("NOME");
        String id = intent.getStringExtra("ID");

        TextView nomeDeUsuario = findViewById(R.id.textViewOla_Home);
        ImageView btnSair = findViewById(R.id.imageViewSair_Home);
        ImageView btnFichaTreino = findViewById(R.id.FichaTreino_Home);
        ImageView btnAmigos = findViewById(R.id.Amigos_Home);

        nomeDeUsuario.setText("Olá, " + nome);
        btnFichaTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, Activity_FichaDeTreinos.class);
                startActivity(intent);
            }
        });

        btnAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, Activity_ListaDeAmigos.class);
                startActivity(intent);

            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, Activity_Login.class);
                startActivity(intent);

            }
        });


    }
}
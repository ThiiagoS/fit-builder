package activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import cruds.User;

public class Activity_Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_perfil), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        User user = new User(this);

        Intent intent = getIntent();

        String nome = intent.getStringExtra("NOME");
        String email = intent.getStringExtra("EMAIL");
        String senha = intent.getStringExtra("SENHA");

        Button btnContinuar = findViewById(R.id.buttonContinue_Perfil);

        ImageButton btnHomem = findViewById(R.id.buttonMale_Perfil);
        ImageButton btnMulher = findViewById(R.id.buttonFemale_Perfil);

        SeekBar seekBarWeight = findViewById(R.id.seekBarWeight_Perfil);
        final TextView textViewWeight = findViewById(R.id.textViewWeight_Perfil);

        SeekBar seekBarPeso = findViewById(R.id.seekBarPeso_Perfil);
        final TextView textViewPeso = findViewById(R.id.textViewPeso_Perfil);

        EditText idadePerfil = findViewById(R.id.editTextAge_Perfil);
        String genero = "";

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String altura = textViewWeight.getText().toString();
                String peso = textViewPeso.getText().toString();
                Integer idade = Integer.valueOf(idadePerfil.getText().toString());



                user.addUser(nome,email,senha,genero,idade,altura,peso);

                Intent intent = new Intent(Activity_Perfil.this, Activity_Home.class);
                startActivity(intent);

                // Cursor cursor = user.getAllUsers();
                // if(cursor.moveToFirst()){
                //     do {
                //         int indexNome = cursor.getColumnIndex("name");
                //         int indexEmail = cursor.getColumnIndex("email");

                //         String nome = cursor.getString(indexNome);
                //         String email = cursor.getString(indexEmail);
                //     } while (cursor.moveToNext());
                // }
                
            }
        });

        btnHomem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genero = "Homem";
            }
        });

        btnMulher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genero = "Mulher";
            }
        });

        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewWeight.setText(progress + " cm");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarPeso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewPeso.setText(progress + " kg");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

}
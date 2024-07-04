package activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
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

import java.util.Objects;

import cruds.User;
import utils.Dialog;

public class Activity_Perfil extends AppCompatActivity {

    private String genero;
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
        genero = "";

        int blueColor = Color.parseColor("#32aeb7");

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String altura = textViewWeight.getText().toString();
                String peso = textViewPeso.getText().toString();
                String idade = idadePerfil.getText().toString();

                if (genero.isEmpty()) {
                    Dialog.showErrorDialog(Activity_Perfil.this, "Erro", "O gênero deve ser preenchido.");
                    return;
                }

                if (idade.isEmpty()) {
                    Dialog.showErrorDialog(Activity_Perfil.this, "Erro", "A idade deve ser preenchida.");
                    return;
                }

                if (altura.isEmpty()) {
                    Dialog.showErrorDialog(Activity_Perfil.this, "Erro", "A altura deve ser preenchida.");
                    return;
                }

                if (peso.isEmpty()) {
                    Dialog.showErrorDialog(Activity_Perfil.this, "Erro", "O peso deve ser preenchida.");
                    return;
                }

                user.addUser(nome,email,senha,genero,Integer.valueOf(idade),altura,peso);

                String ID = "";
                Cursor cursor = user.getAllUsers();
                if (cursor.moveToFirst()) {
                    do {
                        int indexNome = cursor.getColumnIndex("name");
                        int indexSenha = cursor.getColumnIndex("password");
                        int indexID = cursor.getColumnIndex("id");

                        String nomeUsuarioCadastrado = cursor.getString(indexNome);
                        String senhaUsuarioCadastrado = cursor.getString(indexSenha);
                        String idUsuarioCadatrado = cursor.getString(indexID);

                        if(Objects.equals(nome, nomeUsuarioCadastrado) && Objects.equals(senha, senhaUsuarioCadastrado)){
                            ID = idUsuarioCadatrado;
                        }

                    } while (cursor.moveToNext());
                }

                Intent intent = new Intent(Activity_Perfil.this, Activity_Home.class);

                intent.putExtra("ID", ID);
                intent.putExtra("NOME", nome);
                startActivity(intent);

            }
        });

        btnHomem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHomem.setBackgroundTintList(ColorStateList.valueOf(blueColor));
                btnMulher.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                genero = "Homem";
            }
        });

        btnMulher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHomem.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                btnMulher.setBackgroundTintList(ColorStateList.valueOf(blueColor));
                genero = "Mulher";
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
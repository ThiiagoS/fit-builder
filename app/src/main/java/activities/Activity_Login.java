package activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import java.util.Objects;

import cruds.User;
import utils.Dialog;

public class Activity_Login extends AppCompatActivity {

    Button btnCriarConta;
    Button btnAcessarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User user = new User(this);

        btnCriarConta = findViewById(R.id.btnCriarConta_Login);
        btnAcessarConta = findViewById(R.id.btnCriarContaAcessar_Login);

        EditText nomeUsuario = findViewById(R.id.editNome_Login);
        EditText senhaUsuario = findViewById(R.id.editSenha_Login);

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Login.this, Activity_CriarConta.class);
                startActivity(intent);
            }
        });

        btnAcessarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeUsuario.getText().toString();
                String senha = senhaUsuario.getText().toString();
                String ID = "";
                Boolean credenciasCorretas = false;
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
                            credenciasCorretas = true;
                            ID = idUsuarioCadatrado;
                        }

                    } while (cursor.moveToNext());
                }

                if(!credenciasCorretas){
                    Dialog.showErrorDialog(Activity_Login.this, "Erro", "Credencias incorretas.");
                    return;
                }

                Intent intent = new Intent(Activity_Login.this, Activity_Home.class);

                intent.putExtra("ID", ID);
                intent.putExtra("NOME", nome);

                startActivity(intent);
            }
        });
    }
}
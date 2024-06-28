package activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

public class Activity_CriarConta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_criar_conta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CriarConta), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        User user = new User(this);

        Button btnVoltar = findViewById(R.id.btnVoltar_CriarConta);
        Button btnCriarAcessar = findViewById(R.id.btnCriarContaAcessar_CriarConta);

        EditText nomeUsuario = findViewById(R.id.editNome_CriarConta);
        EditText emailUsuario = findViewById(R.id.editEmail_CriarConta);
        EditText senhaUsuario = findViewById(R.id.editSenha_CriarConta);
        EditText confirmaSenhaUsuario = findViewById(R.id.editConfirmaSenha_CriarConta);

        btnCriarAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeUsuario.getText().toString();
                String email = emailUsuario.getText().toString();
                String senha = senhaUsuario.getText().toString();
                String confirmaSenha = confirmaSenhaUsuario.getText().toString();

                if (nome.isEmpty()) {
                    Dialog.showErrorDialog(Activity_CriarConta.this, "Erro", "O nome não pode ser vazio.");
                    return;
                }

                if (email.isEmpty()) {
                    Dialog.showErrorDialog(Activity_CriarConta.this, "Erro", "O email não pode ser vazio.");
                    return;
                }

                if (senha.isEmpty()) {
                    Dialog.showErrorDialog(Activity_CriarConta.this, "Erro", "A senha não pode ser vazia.");
                    return;
                }

                if (!senha.equals(confirmaSenha)) {
                    Dialog.showErrorDialog(Activity_CriarConta.this, "Erro", "A senha não esta igual nos dois campos.");
                    return;
                }

                Cursor cursor = user.getAllUsers();
                if (cursor.moveToFirst()) {
                    do {
                        int indexEmail = cursor.getColumnIndex("email");
                        String emailUsuarioCadastrado = cursor.getString(indexEmail);

                        if(Objects.equals(emailUsuarioCadastrado, email)){
                            Dialog.showErrorDialog(Activity_CriarConta.this, "Erro", "Email já esta cadastrado.");
                            return;
                        }
                    } while (cursor.moveToNext());
                }

                Intent intent = new Intent(Activity_CriarConta.this, Activity_Perfil.class);

                intent.putExtra("NOME", nome);
                intent.putExtra("EMAIL", email);
                intent.putExtra("SENHA", senha);

                startActivity(intent);
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
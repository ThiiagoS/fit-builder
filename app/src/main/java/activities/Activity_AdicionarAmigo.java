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
import cruds.User;
import utils.Dialog;

public class Activity_AdicionarAmigo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_amigo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Friends amigoRelacao = new Friends(this);
        User user = new User(this);

        ImageView btnVoltar = findViewById(R.id.btnvoltar_ListaDeAmigos);
        Button btnAdicionar = findViewById(R.id.editAdicionarAmigo_AdicionarAmigo);

        EditText emailAmigo = findViewById(R.id.editNomeExercicio_AdicionarAmigo);

        Intent intent = getIntent();
        String idUser = intent.getStringExtra("ID");

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailDoAmigo = emailAmigo.getText().toString();

                String ID = "";
                String Nome = "";
                Boolean usuarioEncontrado = false;
                Cursor cursor = user.getAllUsers();
                if (cursor.moveToFirst()) {
                    do {
                        int indexNome = cursor.getColumnIndex("name");
                        int indexEmail = cursor.getColumnIndex("email");
                        int indexID = cursor.getColumnIndex("id");

                        String nomeUsuarioCadastrado = cursor.getString(indexNome);
                        String emailUsuarioCadastrado = cursor.getString(indexEmail);
                        String idUsuarioCadatrado = cursor.getString(indexID);

                        if(Objects.equals(emailDoAmigo, emailUsuarioCadastrado) && !Objects.equals(idUser, idUsuarioCadatrado)){
                            usuarioEncontrado = true;
                            ID = idUsuarioCadatrado;
                            Nome = nomeUsuarioCadastrado;
                        }

                    } while (cursor.moveToNext());
                }

                if(!usuarioEncontrado){
                    Dialog.showErrorDialog(Activity_AdicionarAmigo.this, "Erro", "Este usuario que você esta tentando adicionar como amigo não esta cadastrado no sistema..");
                    return;
                }

                Cursor cursorAmigo = amigoRelacao.getAllFriends();
                if (cursorAmigo.moveToFirst()) {
                    do {
                        int indexEmail = cursorAmigo.getColumnIndex("email");
                        int indexUserFriendID = cursorAmigo.getColumnIndex("userFriend_id");

                        String emailUserFriend = cursorAmigo.getString(indexEmail);
                        String idUserFriend = cursorAmigo.getString(indexUserFriendID);

                        if (Objects.equals(emailUserFriend, emailDoAmigo) && Objects.equals(idUserFriend, idUser) ) {
                            Dialog.showErrorDialog(Activity_AdicionarAmigo.this, "Erro", "Este amigo ja foi adicionado.");
                            return;
                        }

                    } while (cursorAmigo.moveToNext());
                }

                amigoRelacao.addFriend(Integer.parseInt(idUser),Nome,emailDoAmigo);
                finish();
            }
        });
    }
}
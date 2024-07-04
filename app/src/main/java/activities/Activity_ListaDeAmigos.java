package activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitbuilder.R;

import java.util.ArrayList;
import java.util.Objects;

import cruds.Friends;

public class Activity_ListaDeAmigos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_de_amigos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listaDeAmigos_ListaDeAmigos), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        String id = intent.getStringExtra("ID");

        Friends amigoRelacao = new Friends(this);

        ImageView btnVoltar = findViewById(R.id.btnvoltar_ListaDeAmigos);
        Button btnAdicionarAmigo = findViewById(R.id.buttonAdicionarAmigo_ListaDeAmigos);

        LinearLayout buttonContainer = findViewById(R.id.button_container);

        ArrayList<String> buttonNames = new ArrayList<>();
        ArrayList<Integer> buttonIds = new ArrayList<>();

        Cursor cursor = amigoRelacao.getAllFriends();
        if (cursor.moveToFirst()) {
            do {
                int indexNome = cursor.getColumnIndex("name");
                int indexUserFriend = cursor.getColumnIndex("userFriend_id");
                int indexId = cursor.getColumnIndex("id");

                String nomeUserFriend = cursor.getString(indexUserFriend);
                String nomeFriend = cursor.getString(indexNome);
                String idFriend = cursor.getString(indexId);

                if (Objects.equals(nomeUserFriend, id)) {
                    buttonNames.add(nomeFriend);
                    buttonIds.add(Integer.parseInt(idFriend));
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_ListaDeAmigos.this);
                        builder.setTitle("Confirmação");
                        builder.setMessage("Você deseja excluir este amigo?");

                        builder.setPositiveButton("Sim", (dialog, which) -> {
                            amigoRelacao.deleteFriend(buttonIds.get(finalIndex));
                            finish();
                        });

                        builder.setNegativeButton("Não", (dialog, which) -> {

                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
            );
            index++;
        }


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicionarAmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_ListaDeAmigos.this, Activity_AdicionarAmigo.class);
                intent.putExtra("ID", id);
                startActivity(intent);
                finish();
            }
        });
    }
}

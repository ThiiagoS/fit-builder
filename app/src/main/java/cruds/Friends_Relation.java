package cruds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import database.DatabaseHelper;

public class Friends_Relation extends DatabaseHelper {

    public Friends_Relation(Context context) {
        super(context);
    }

    public void addRelationFriend(int usuarioId, int amigoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", usuarioId);
        values.put("friend_id", amigoId);
        db.insert("friends_relation", null, values);
        db.close();
    }

    public Cursor listFriendsUser(int usuarioId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT amigos.id, amigos.nome FROM amigos " +
                "INNER JOIN amigos_relacoes ON amigos.id = amigos_relacoes.amigo_id " +
                "WHERE amigos_relacoes.usuario_id = ?";
        return db.rawQuery(query, new String[]{String.valueOf(usuarioId)});
    }

    public void deletarRelacaoAmizade(int userId, int friendId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("friends_relation", "user_id = ? AND friend_id = ?",
                new String[]{String.valueOf(userId), String.valueOf(friendId)});
        db.close();
    }
}
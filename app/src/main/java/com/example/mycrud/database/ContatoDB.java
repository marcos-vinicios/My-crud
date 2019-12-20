package com.example.mycrud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mycrud.entidades.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDB extends SQLiteOpenHelper {

    private static String dbNome = "contatoDB";
    private static String tableNome = "contato";
    private static String idColuna = "id";
    private static String nomeColuna = "nome";
    private static String telefoneColuna = "telefone";
    private Context context;

    public ContatoDB(Context context) {
        super(context, dbNome, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tableNome + " (" +
                idColuna + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                nomeColuna + " text, " +
                telefoneColuna + " text " +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableNome);
        onCreate(db);
    }

    public List<Contato> findAll(){
        try{
            List<Contato> contatos = new ArrayList<Contato>(); // lista criada
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery( " SELECT * FROM " + tableNome, null);
            if (cursor.moveToFirst()){
                do {
                    Contato contato = new Contato();
                    contato.setId(cursor.getInt(0));
                    contato.setNome(cursor.getString(1));
                    contato.setTelefone(cursor.getString(2));
                    contatos.add(contato);//add na lista
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();//fecha conexão DB
            return contatos;
        }catch (Exception e){
            return null;
        }
    }
    public boolean create(Contato contato){
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nomeColuna, contato.getNome());
            contentValues.put(telefoneColuna, contato.getTelefone());
            long rows = sqLiteDatabase.insert(tableNome, null, contentValues);
            sqLiteDatabase.close();
            return rows > 0;
        }catch (Exception e){
            return false;
        }
    }
    public boolean delete(int id){
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            int rows = sqLiteDatabase.delete(tableNome, idColuna + " = ?", new String[] {String.valueOf(id)});
            return rows > 0;
        }catch (Exception e){
            return false;
        }
    }
    public Contato find(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableNome +
                    " where " + idColuna + " = ?", new String[]{String.valueOf(id)});

            Contato contato = null;
            if (cursor.moveToFirst()) {
                contato = new Contato();
                contato.setId(cursor.getInt(0));
                contato.setNome(cursor.getString(1));
                contato.setTelefone(cursor.getString(2));
            }
            sqLiteDatabase.close();
            return contato;

        } catch (Exception e) {
            return null;
        }
    }
    public boolean update(Contato contato){
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nomeColuna, contato.getNome());
            contentValues.put(telefoneColuna, contato.getTelefone());
            int rows = sqLiteDatabase.update(tableNome, contentValues, idColuna + " = ?",
                    new String[] {String.valueOf(contato.getId())});
            sqLiteDatabase.close();
            return rows > 0;
        }catch (Exception e){
            return false;
        }

    }
}

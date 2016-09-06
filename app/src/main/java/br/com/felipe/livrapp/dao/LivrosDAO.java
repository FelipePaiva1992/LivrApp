package br.com.felipe.livrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.felipe.livrapp.model.Livro;

/**
 * Created by RM30381 on 05/09/2016.
 */
public class LivrosDAO {

    private final String TABELA_LIVROS = "livros";
    private final String KEY           = "id";
    private final String KEY_TITULO        = "titulo";
    private final String KEY_AUTOR     = "autor";
    private final String[] colunas = {KEY,KEY_TITULO,KEY_AUTOR};

    private MySQLiteHelper dbHelper;

    public LivrosDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void add(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(KEY_TITULO, livro.getTitulo());
        valores.put(KEY_AUTOR, livro.getAutor());

        db.insert(TABELA_LIVROS,null,valores);

        db.close();
    }

    public Livro get(int id){
        Livro livro = new Livro();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TABELA_LIVROS,
                colunas,
                " id = ? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);


        if(cursor != null){
            cursor.moveToFirst();
            livro.setId(cursor.getInt(0));
            livro.setAutor(cursor.getString(1));
            livro.setTitulo(cursor.getString(2));
        }
        db.close();

        return livro;
    }

    public List<Livro> getAll(){
        List<Livro> livros = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + TABELA_LIVROS;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                Livro livro = new Livro();
                livro.setId(cursor.getInt(cursor.getColumnIndex(KEY)));
                livro.setAutor(cursor.getString(cursor.getColumnIndex(KEY_AUTOR)));
                livro.setTitulo(cursor.getString(cursor.getColumnIndex(KEY_TITULO)));
                livros.add(livro);
            } while (cursor.moveToNext());
        }

        return livros;
    }

    public void update(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(KEY_TITULO, livro.getTitulo());
        valores.put(KEY_AUTOR, livro.getAutor());

        db.update(TABELA_LIVROS,valores, KEY  + " = ? ", new String[]{String.valueOf(livro.getId())});

        db.close();
    }

    public void delete(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABELA_LIVROS, KEY + " = ?", new String[]{String.valueOf(livro.getId())});
        db.close();
    }
}

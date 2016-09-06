package br.com.felipe.livrapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RM30381 on 05/09/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public MySQLiteHelper(Context context) {
        super(context, "livros", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criar_tabel_livros = "CREATE OR REPLACE TABLE livros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "autor TEXT)";

        db.execSQL(criar_tabel_livros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF EXISTS livros");
        onCreate(db);
    }
}

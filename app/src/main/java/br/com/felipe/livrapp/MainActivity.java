package br.com.felipe.livrapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import br.com.felipe.livrapp.dao.LivrosDAO;
import br.com.felipe.livrapp.model.Livro;

public class MainActivity extends AppCompatActivity {

    private final String PREF_NAME = "LIVRARIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        testeDB();
    }

    private void testeDB(){
        LivrosDAO dao = new LivrosDAO(this);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        if(!sp.getBoolean("JAINSERIU",false)) {
            dao.add(new Livro("Google Android", "Ricardo Lachetta"));
            dao.add(new Livro("Crepusculo", "Stepheie Meyer"));
            dao.add(new Livro("50 Tons de Cinza", "E L James"));

            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("JAINSERIU", true);
            editor.commit();
        }

        List<Livro> livros = dao.getAll();

        for(Livro l:livros){
            Log.i("LIVRO", l.getTitulo());
        }
    }

}

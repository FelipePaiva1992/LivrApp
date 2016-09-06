package br.com.felipe.livrapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private TextInputLayout tiUsuario;
    private TextInputLayout tiSenha;
    private CheckBox cbManterConectado;
    private final String PREF_NAME = "LIVRARIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tiUsuario = (TextInputLayout)findViewById(R.id.tilLogin);
        tiSenha = (TextInputLayout)findViewById(R.id.tilSenha);
        cbManterConectado = (CheckBox)findViewById(R.id.cbManterLogado);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if(sp.getBoolean("manterConectado", false)){
            proximaTela(sp.getString("nomeUsuario", ""));
        }

    }

    public void logar(View v){
        String usuario = tiUsuario.getEditText().getText().toString();
        String senha = tiSenha.getEditText().getText().toString();
        if(usuario.toUpperCase().equals("FIAP")
                && senha.equals("123")){

                SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("manterConectado", cbManterConectado.isChecked());
                editor.putString("nomeUsuario", usuario);
                editor.commit();

            proximaTela(usuario);

        }else {
            Toast.makeText(this, "Você errou seu burro", Toast.LENGTH_LONG).show();
        }
    }

    private void proximaTela(String usuario) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("nome", usuario);
        startActivity(intent);
        finish();
    }

}

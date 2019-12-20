package com.example.mycrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mycrud.database.ContatoDB;
import com.example.mycrud.entidades.Contato;

public class AddContatoActivity extends AppCompatActivity {
    private Button buttonVolta, buttonSalva;
    private EditText editTextNome, editTextTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contato);

        this.editTextNome = findViewById(R.id.editTextNome);
        this.editTextTelefone = findViewById(R.id.editTextTelefone);
        this.buttonVolta =  findViewById(R.id.buttonVolta);
        buttonVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContatoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        this.buttonSalva = findViewById(R.id.buttonSalva);
        buttonSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContatoDB  contatoDB = new ContatoDB(getBaseContext());
                Contato contato = new Contato();
                contato.setNome(editTextNome.getText().toString());
                contato.setTelefone(editTextTelefone.getText().toString());
                if (contatoDB.create(contato)){
                    Intent intent = new Intent(AddContatoActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Erro");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                }

            }
        });
    }
}

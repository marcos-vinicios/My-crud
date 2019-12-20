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

public class EditContatoActivity extends AppCompatActivity {
        private EditText editTextNome, editTextTelefone;
        private Button buttonVolta1, buttonSalva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contato);
        Intent intent = getIntent();
        final Contato contato = (Contato) intent.getSerializableExtra("contato");
        this.editTextNome = findViewById(R.id.editTextNome3);
        this.editTextNome.setText(contato.getNome());
        this.editTextTelefone = findViewById(R.id.editTextTelefone3);
        this.editTextTelefone.setText(contato.getTelefone());
        this.buttonVolta1 = findViewById(R.id.buttonVolta2);
        buttonVolta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditContatoActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        this.buttonSalva = findViewById(R.id.buttonSalva2);
        this.buttonSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContatoDB contatoDB = new ContatoDB(getBaseContext());
                contato.setNome(editTextNome.getText().toString());
                contato.setTelefone(editTextTelefone.getText().toString());
                if (contatoDB.update(contato)){
                    Intent intent1 = new Intent(EditContatoActivity.this, MainActivity.class);
                    startActivity(intent1);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Fail");
                        builder.setCancelable(false);
                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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

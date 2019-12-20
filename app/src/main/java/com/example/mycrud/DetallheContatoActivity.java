package com.example.mycrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mycrud.database.ContatoDB;
import com.example.mycrud.entidades.Contato;

public class DetallheContatoActivity extends AppCompatActivity {
    private TextView textViewNome, textViewTelefone;
    private Button buttonVolta, buttonDelete, buttonEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallhe_contato);
        Intent intent = getIntent();
        final Contato contato = (Contato) intent.getSerializableExtra("contato");

        this.textViewNome = findViewById(R.id.textViewNome1);
        this.textViewNome.setText(contato.getNome());
        this.textViewTelefone = findViewById(R.id.textViewTelefone);
        this.textViewTelefone.setText(contato.getTelefone());
        this.buttonVolta = findViewById(R.id.buttonVolta);
        this.buttonVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetallheContatoActivity.this, MainActivity.class);
                i.putExtra("contato", contato);
                startActivity(i);
            }
        });

        this.buttonEditar = findViewById(R.id.buttonEditar);
        this.buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetallheContatoActivity.this, EditContatoActivity.class);
                intent1.putExtra("contato", contato);
                startActivity(intent1);
            }
        });


        this.buttonDelete = findViewById(R.id.buttonDeletar);
        this.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(false);
                builder.setTitle("confirma");
                builder.setMessage("Deseja deletar?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContatoDB contatoDB = new ContatoDB(getBaseContext());

                        if (contatoDB.delete(contato.getId())){
                            Intent intent1 = new Intent( DetallheContatoActivity.this, MainActivity.class);
                            startActivity(intent1);
                        }else {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext());
                            builder1.setCancelable(false);
                            builder1.setMessage("Erro");
                            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder1.create().show();
                        }
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

    }
}

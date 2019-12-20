package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mycrud.adpater.ContatoListAdpater;
import com.example.mycrud.database.ContatoDB;
import com.example.mycrud.entidades.Contato;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd;
    private ListView listViewContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonAdd = findViewById(R.id.buttonAdd);
        this.listViewContatos = findViewById(R.id.listContatos);

        this.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddContatoActivity.class);
                startActivity(i);
            }
        });

        final ContatoDB contatoDB = new ContatoDB(this);
        this.listViewContatos = findViewById(R.id.listContatos);
        this.listViewContatos.setAdapter(new ContatoListAdpater(this, contatoDB.findAll()));
        this.listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = contatoDB.findAll().get(position);

                Intent intent = new Intent(MainActivity.this, DetallheContatoActivity.class);
                intent.putExtra("contato", contato);
                startActivity(intent);
            }
        });
    }
}

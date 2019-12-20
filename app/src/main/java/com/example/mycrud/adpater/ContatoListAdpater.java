package com.example.mycrud.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mycrud.R;
import com.example.mycrud.entidades.Contato;

import java.util.List;

public class ContatoListAdpater extends ArrayAdapter<Contato> {
    private Context context ;
    private List<Contato> contatos;

    public ContatoListAdpater(Context context, List<Contato> contatos){
        super(context, R.layout.contato_layout, contatos);
        this.context = context;
        this.contatos = contatos;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.contato_layout, parent, false);
        TextView textViewNome = view.findViewById(R.id.textViewNome);
        textViewNome.setText(contatos.get(position).getNome());
        TextView textViewTelefones = view.findViewById(R.id.textViewTelefones);
        textViewTelefones.setText(contatos.get(position).getTelefone());
        return view;
    }
}

package com.example.joaoelolis.medicinesearchv2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;

import com.example.joaoelolis.medicinesearchv2.R;
import com.example.joaoelolis.medicinesearchv2.modelos.Medicamento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultaAdapter extends ArrayAdapter<Medicamento> {

    private Context context;
    private List<Medicamento> medicamentos;

    public ConsultaAdapter(Context context, ArrayList<Medicamento> medicamentos){
        super(context,0,medicamentos);
        this.context = context;
        this.medicamentos = medicamentos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listaItem = convertView;

        if(listaItem == null){
            listaItem = LayoutInflater.from(context).inflate(R.layout.consulta_lista_medicamento, parent,false);
        }

        Medicamento medicamentoSelecionado = medicamentos.get(position);

        TextView textViewNome = listaItem.findViewById(R.id.txtNomeMedicamento);
        TextView textViewBula = listaItem.findViewById(R.id.txtBulaMedicamento);
        TextView textViewObs = listaItem.findViewById(R.id.txtObsMedicamento);

        textViewNome.setText(medicamentoSelecionado.getNome().substring(0,1).toUpperCase() +
                medicamentoSelecionado.getNome().substring(1));
        textViewBula.setText(medicamentoSelecionado.getBula().substring(0,1).toUpperCase() +
                medicamentoSelecionado.getBula().substring(1));
        textViewObs.setText(medicamentoSelecionado.getObservacao().substring(0,1).toUpperCase()+
                medicamentoSelecionado.getObservacao().substring(1));

        return listaItem;

    }

}
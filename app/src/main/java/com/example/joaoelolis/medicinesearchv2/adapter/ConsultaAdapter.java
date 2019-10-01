package com.example.joaoelolis.medicinesearchv2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        final Button btnBula = listaItem.findViewById(R.id.btnBula);
        final Button btnVoltarBula = listaItem.findViewById(R.id.btnVoltarBula);
        final TextView textViewNome = listaItem.findViewById(R.id.txtNomeMedicamento);
        final TextView textViewBula = listaItem.findViewById(R.id.txtBulaMedicamento);
        final TextView textViewObs = listaItem.findViewById(R.id.txtObsMedicamento);

        btnBula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewBula.setVisibility(View.VISIBLE);
                textViewNome.setVisibility(View.INVISIBLE);
                textViewObs.setVisibility(View.INVISIBLE);
                btnBula.setVisibility(View.INVISIBLE);
                btnVoltarBula.setVisibility(View.VISIBLE);
            }
        });

        btnVoltarBula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewBula.setVisibility(View.INVISIBLE);
                textViewNome.setVisibility(View.VISIBLE);
                textViewObs.setVisibility(View.VISIBLE);
                btnBula.setVisibility(View.VISIBLE);
                btnVoltarBula.setVisibility(View.INVISIBLE);
            }
        });



        textViewNome.setText(medicamentoSelecionado.getNome().substring(0,1).toUpperCase() +
                medicamentoSelecionado.getNome().substring(1));
        textViewBula.setText(medicamentoSelecionado.getBula().substring(0,1).toUpperCase() +
                medicamentoSelecionado.getBula().substring(1));
        textViewObs.setText(medicamentoSelecionado.getObservacao().substring(0,1).toUpperCase()+
                medicamentoSelecionado.getObservacao().substring(1));

        return listaItem;

    }

}

package com.example.joaoelolis.medicinesearchv2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joaoelolis.medicinesearchv2.R;
import com.example.joaoelolis.medicinesearchv2.modelos.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class Consulta_Adapter extends ArrayAdapter<Medicamento> {

    private Context context;
    private List<Medicamento> medicamentos;

    public Consulta_Adapter(Context context, ArrayList<Medicamento> medicamentos){
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

        textViewNome.setText(medicamentoSelecionado.getNome());
        textViewBula.setText(medicamentoSelecionado.getBula());
        textViewObs.setText(medicamentoSelecionado.getObservacao());

        return listaItem;

    }

}

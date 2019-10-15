package com.example.joaoelolis.medicinesearchv2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joaoelolis.medicinesearchv2.adapter.ConsultaAdapter;
import com.example.joaoelolis.medicinesearchv2.modelos.Medicamento;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ListView listView;
    private List<Medicamento> medicamentos = new ArrayList<>();
    private ArrayAdapter<Medicamento> arrayAdapter;
    private Medicamento medicamentoSelecionado;
    private EditText editTextConsulta;
    private TextView textViewBula;
    private TextView textViewNomeMedicamento;
    private TextView textViewObsMedicamento;
    private Button btnBula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        listView = findViewById(R.id.ListViewConsuta);
        editTextConsulta = findViewById(R.id.editTextConsulta);
        textViewNomeMedicamento = findViewById(R.id.txtNomeMedicamento);
        textViewBula = findViewById(R.id.txtBulaMedicamento);
        textViewObsMedicamento = findViewById(R.id.txtObsMedicamento);
        btnBula = findViewById(R.id.btnBula);

        conectarBanco();
        leituraBanco();
        pesquisaLista();

    }

    public void leituraBanco(){
        databaseReference.child("lista de medicamento").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //contruir o listview com os dados do banco

                medicamentos.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Medicamento medicamento = snapshot.getValue(Medicamento.class);
                    medicamentos.add(medicamento);
                }

                arrayAdapter = new ConsultaAdapter(ConsultaActivity.this, (ArrayList<Medicamento>) medicamentos);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void conectarBanco(){

        FirebaseApp.initializeApp(ConsultaActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    public void pesquisaLista(){

        EditText searchTo = (EditText)findViewById(R.id.editTextConsulta);
        searchTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listView.setAdapter(null);

                databaseReference
                        .child("lista de medicamento")
                        .orderByChild("favoritos")
                        .startAt(editTextConsulta.getText().toString().toLowerCase())
                        .endAt(editTextConsulta.getText().toString().toLowerCase()+"\uf8ff")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                //contruir o listview com os dados do banco

                                medicamentos.clear();
                                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                    Medicamento medicamento = snapshot.getValue(Medicamento.class);
                                    medicamentos.add(medicamento);
                                }

                                arrayAdapter = new ConsultaAdapter(ConsultaActivity.this, (ArrayList<Medicamento>) medicamentos);
                                listView.setAdapter(arrayAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        });}

        /*public void pesquisaFavoritos(View view) {

            listView.setAdapter(null);

            databaseReference
                    .child("usuarios")
                    .orderByChild("favoritos")
                    .startAt(editTextConsulta.getText().toString().toLowerCase())
                    .endAt(editTextConsulta.getText().toString().toLowerCase()+"\uf8ff")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //contruir o listview com os dados do banco

                            medicamentos.clear();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Medicamento medicamento = snapshot.getValue(Medicamento.class);
                                medicamentos.add(medicamento);
                            }

                            arrayAdapter = new ConsultaAdapter(ConsultaActivity.this, (ArrayList<Medicamento>) medicamentos);
                            listView.setAdapter(arrayAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


        }*/

    public void chamarBula(View view){
        textViewBula.setVisibility(View.VISIBLE);
        textViewNomeMedicamento.setVisibility(View.INVISIBLE);
        textViewObsMedicamento.setVisibility(View.INVISIBLE);
        btnBula.setVisibility(View.INVISIBLE);
    }

    public void chamarFavoritos(View view){



    }

}

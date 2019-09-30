package com.example.joaoelolis.medicinesearchv2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.joaoelolis.medicinesearchv2.adapter.Consulta_Adapter;
import com.example.joaoelolis.medicinesearchv2.modelos.Medicamento;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        listView = findViewById(R.id.ListViewConsuta);
        editTextConsulta = findViewById(R.id.editTextConsulta);

        conectarBanco();
        leituraBanco();

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

                arrayAdapter = new Consulta_Adapter(ConsultaActivity.this, (ArrayList<Medicamento>) medicamentos);
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

    public void chamarConsulta(View view){

        listView.setAdapter(null);

        databaseReference
                .child("lista de medicamento")
                .orderByChild("nome")
                .startAt(editTextConsulta.getText().toString())
                .endAt(editTextConsulta.getText().toString()+"\uf8ff")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //contruir o listview com os dados do banco

                medicamentos.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Medicamento medicamento = snapshot.getValue(Medicamento.class);
                    medicamentos.add(medicamento);
                }

                arrayAdapter = new Consulta_Adapter(ConsultaActivity.this, (ArrayList<Medicamento>) medicamentos);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

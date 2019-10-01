package com.example.joaoelolis.medicinesearchv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joaoelolis.medicinesearchv2.modelos.Medicamento;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cadastrar extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Medicamento> medicamentos = new ArrayList<>();
    private EditText editTextNome;
    private EditText editTextBula;
    private EditText editTextObservacoes;
    private TextView textViewNomeMedicamento;
    private TextView textViewBulaMedicamento;
    private TextView textViewObsMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        editTextNome = findViewById(R.id.edtCadastraNome);
        editTextBula = findViewById(R.id.edtBula);
        editTextObservacoes = findViewById(R.id.edtObservacao);
        textViewNomeMedicamento =findViewById(R.id.txtNomeMedicamento);
        textViewBulaMedicamento =findViewById(R.id.txtBulaMedicamento);
        textViewObsMedicamento =findViewById(R.id.txtObsMedicamento);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(Cadastrar.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarDado(View view){


        editTextNome = findViewById(R.id.edtCadastraNome);
        editTextBula = findViewById(R.id.edtBula);
        editTextObservacoes = findViewById(R.id.edtObservacao);

        final Medicamento medicamento = new Medicamento(editTextNome.getText().toString(),
                editTextBula.getText().toString(),
                editTextObservacoes.getText().toString());

        String uuid = UUID.randomUUID().toString();//Gerando um numero aleatorio.

        databaseReference
                .child("lista de medicamento")
                .orderByChild("nome")
                .equalTo(editTextNome.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //contruir o listview com os dados do banco

                        medicamentos.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Medicamento medicamento = snapshot.getValue(Medicamento.class);
                            medicamentos.add(medicamento);
                        }
                        if (medicamentos.size() == 0) {

                            databaseReference
                                    .child("lista de medicamento")
                                    .child(medicamento.getNome())
                                    .setValue(medicamento);

                            Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();

                            /*Intent intent = new Intent(this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);*/
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Medicamento já cadastrado", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void ChamarMenuInicial(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}

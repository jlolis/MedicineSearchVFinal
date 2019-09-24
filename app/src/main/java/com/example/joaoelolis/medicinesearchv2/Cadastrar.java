package com.example.joaoelolis.medicinesearchv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.joaoelolis.medicinesearchv2.modelos.Medicamento;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Cadastrar extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText editTextNome;
    private EditText editTextBula;
    private EditText editTextObservacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        editTextNome = findViewById(R.id.edtCadastraNome);
        editTextBula = findViewById(R.id.edtBula);
        editTextObservacoes = findViewById(R.id.edtObservacao);

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

        Medicamento medicamento = new Medicamento(editTextNome.getText().toString(),
                editTextBula.getText().toString(),
                editTextObservacoes.getText().toString());


        String uuid = UUID.randomUUID().toString();//Gerando um numero aleatorio.

        //inserindo Dado no FireBase lista de medicamento(conjunto) de chaves e valores .
        databaseReference
                .child("lista de medicamento")
                .child(uuid)
                .setValue(medicamento);

        /*editTextNome.setText("");
        editTextBula.setText("");
        editTextObservacoes.setText("");*/
    }

}

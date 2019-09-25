package com.example.joaoelolis.medicinesearchv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText editTextNome;
    private EditText editTextBula;
    private EditText editTextObservacoes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.edtCadastraNome);
        editTextBula = findViewById(R.id.edtBula);
        editTextObservacoes = findViewById(R.id.edtObservacao);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void salvarDado(View view){

        editTextNome = findViewById(R.id.edtCadastraNome);
        editTextBula = findViewById(R.id.edtBula);
        editTextObservacoes = findViewById(R.id.edtObservacao);

        String uuid = UUID.randomUUID().toString();//Gerando um numero aleatorio.

        String nome = editTextNome.getText().toString();
        String bula = editTextBula.getText().toString();
        String observacao = editTextObservacoes.getText().toString();

        //inserindo Dado no FireBase dicionario(conjunto) de chaves e valores .
        databaseReference
                .child("lista de medicamento")
                .child(uuid)
                .child(nome)
                .child(bula)
                .child(observacao)
                .setValue(editTextNome.getText().toString());

        /*editTextNome.setText("");
        editTextBula.setText("");
        editTextObservacoes.setText("");*/

    }

    public void ChamarCadastro(View view){
        Intent intent = new Intent(this, Cadastrar.class);
        startActivity(intent);    }

    public void ChamarConsulta(View view){
        Intent intent = new Intent(this, ConsultaActivity.class);
        startActivity(intent);    }

}

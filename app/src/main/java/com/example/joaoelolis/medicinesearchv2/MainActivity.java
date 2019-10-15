package com.example.joaoelolis.medicinesearchv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.joaoelolis.medicinesearchv2.modelos.Medicamento;
import com.example.joaoelolis.medicinesearchv2.modelos.Usuario;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPreferences;
    private Usuario user = new Usuario();
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
        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String resultado = sharedPreferences.getString("LOGIN","");

        if(!Boolean.parseBoolean(resultado)){
            criarLogin();
        }

        conectarBanco();

    }

    private void criarLogin(){

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );




        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.LoginThemes)
                        .build(), 123
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 123 && resultCode == RESULT_OK){

            IdpResponse response = IdpResponse.fromResultIntent(data);

            sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LOGIN","true");
            editor.apply();

            if(response.isNewUser()){
                this.user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                this.user.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                databaseReference.child("usuarios").child(user.getId()).setValue(user);
            }

        } else{
            finish();
        }
    }

    public void logout (View v){
        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LOGIN","false");
        editor.apply();
        criarLogin();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void ChamarCadastro(View view){
        Intent intent = new Intent(this, Cadastrar.class);
        startActivity(intent);    }

    public void ChamarConsulta(View view){
        Intent intent = new Intent(this, ConsultaActivity.class);
        startActivity(intent);    }

    public void fechar(View view){
        finish();
    }

}

package com.example.quizr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityCadastro extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button salvarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.cadastro_nome);
        email = findViewById(R.id.cadastro_email);
        senha = findViewById(R.id.cadastro_senha);
        salvarCadastro = findViewById(R.id.salvar_cadastro_oio);

        FirebaseFirestore fb = FirebaseFirestore.getInstance();

        salvarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelUser user = new ModelUser(nome.getText().toString(),email.getText().toString(),senha.getText().toString());

                fb.collection("Usuarios").document(user.getEmail()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(ActivityCadastro.this, "Save", Toast.LENGTH_SHORT).show();

                        Intent openHome = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(openHome);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ActivityCadastro.this, "Failure", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
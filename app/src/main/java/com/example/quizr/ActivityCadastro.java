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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityCadastro extends AppCompatActivity {

    //variaveis da tela
    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button salvarCadastro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //instanciando os botoes e itens do xml
        nome = findViewById(R.id.cadastro_nome);
        email = findViewById(R.id.cadastro_email);
        senha = findViewById(R.id.cadastro_senha);
        salvarCadastro = findViewById(R.id.salvar_cadastro_oio);

        //instancias do banco de dados
        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //funcao para salvar o Usuario
        salvarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //metodo que recebe email e senha para jogar o banco
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //verificar se foi sucesso o metododo mAuth
                        if (task.isSuccessful()){

                            //criando um novo objeto user
                            ModelUser user = new ModelUser(nome.getText().toString(),email.getText().toString(),senha.getText().toString(),0,0);
                            //adicionado o novo usuario no banco
                            fb.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(ActivityCadastro.this, "Cadastrado", Toast.LENGTH_SHORT).show();
                                    Intent loggerScreen = new Intent(getApplicationContext(), HomeUserActivity.class);
                                    startActivity(loggerScreen);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(ActivityCadastro.this, "Não Foi Possível Cadastrar", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ActivityCadastro.this, "Falha", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
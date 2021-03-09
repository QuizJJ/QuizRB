package com.example.quizr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    //instanciando os botoes e variaveis utlizadas na tela Main
    private Button entrar;
    private TextView cadsatrarse;
    private Button jogarSemLogin;
    private EditText emailLog;
    private EditText senhaGet;


    //variaveis utilizadas como parametros do login
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciando os componentes do banco pelo ID
        cadsatrarse= findViewById(R.id.text_cadastrase);
        entrar = findViewById(R.id.button_entrar);
        emailLog = findViewById(R.id.get_email);
        senhaGet = findViewById(R.id.get_senha);

        //pegando a instancia do fireStore
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        //pegando a instacia do fireStore autenticação
        FirebaseAuth Auth = FirebaseAuth.getInstance();



        //funcao de click do botao entrar
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pega email e senha e converte para string
                email = emailLog.getText().toString();
                password = senhaGet.getText().toString();

                //verifica se tem senha e email nos campos.
                if(email.isEmpty()){
                    emailLog.setError("Email Obrigatorio");
                    emailLog.requestFocus();
                }else if(password.isEmpty()){
                    senhaGet.setError("Senha Obrigatoria");
                    senhaGet.requestFocus();
                }else {

                    //meoto de login do usuario já cadastrado.
                    Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(MainActivity.this, "Sucesso Logar", Toast.LENGTH_SHORT).show();

                                // abre uma nova acitivity Home user.
                                Intent inten = new Intent(getApplicationContext(), HomeUserActivity.class);
                                startActivity(inten);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        //funcao de abrir a tela de cadastro de usuario.
        cadsatrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),ActivityCadastro.class);
                startActivity(intent);

            }
        });
    }
}
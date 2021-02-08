package com.example.quizr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private Button entrar;
    private TextView cadsatrarse;
    private Button jogarSemLogin;
    private EditText emailLog;
    private EditText senhaGet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadsatrarse= findViewById(R.id.text_cadastrase);
        entrar = findViewById(R.id.button_entrar);
        emailLog = findViewById(R.id.get_email);
        senhaGet = findViewById(R.id.get_senha);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

//        cadastrar = findViewById(R.id.button_cadastrar);
//        jogarSemLogin = findViewById(R.id.button_entrar_sem_login);

//        entrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getApplicationContext(),ActivityHomeQuiz.class);
//                startActivity(intent);
//
//            }
//        });
//
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailLog.getText().toString().isEmpty()){

                    Toast.makeText(MainActivity.this, "Email Obrigatorio", Toast.LENGTH_SHORT).show();

                }else if (senhaGet.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Senha Obrigatorio", Toast.LENGTH_SHORT).show();
                }else {

                    firestore.collection("Usuarios").document(emailLog.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){

                                DocumentSnapshot documentSnapshot = task.getResult();

                                ModelUser user =  documentSnapshot.toObject(ModelUser.class);

                                if(emailLog.getText().toString().equals(user.getEmail()) ){

                                    Toast.makeText(MainActivity.this, "Email Correto", Toast.LENGTH_SHORT).show();
                                }else {

                                    Toast.makeText(MainActivity.this, "Email Errado", Toast.LENGTH_SHORT).show();

                                }  if (senhaGet.getText().toString().equals(user.getSenha())){
                                    Toast.makeText(MainActivity.this, "Senha correta", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(MainActivity.this, "Senha Errada", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(MainActivity.this, "Documento Não Encontrado", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });



        cadsatrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),ActivityCadastro.class);
                startActivity(intent);

            }
        });

    }
}
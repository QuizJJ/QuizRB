package com.example.quizr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

public class HomeUserActivity extends AppCompatActivity {

    private MaterialButton jogar;
    private MaterialButton upBank;
    private TextView nomeUser;
    private TextView qntPontosUser;
    private TextView qntJogosUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        jogar = findViewById(R.id.play_button);
        upBank = findViewById(R.id.button_id_config);
        nomeUser = findViewById(R.id.nome_do_user);
        qntPontosUser = findViewById(R.id.qnt_pts_user);
        qntJogosUser = findViewById(R.id.qnt_jogos_user);

        //instancias do fb user e banco
        FirebaseAuth user = FirebaseAuth.getInstance();
        FirebaseFirestore fb = FirebaseFirestore.getInstance();

        //pega o nome, qnt pontos , qnt jogos do usuario e seta nos campos da HOme.
        fb.collection("Users").document(user.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot user = task.getResult();
                    nomeUser.setText(user.toObject(ModelUser.class).getNome());
                    qntPontosUser.setText(Integer.toString(user.toObject(ModelUser.class).getPontos()));
                    qntJogosUser.setText(Integer.toString(user.toObject(ModelUser.class).getNumeroJogos()));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeUserActivity.this, "Error Interno", Toast.LENGTH_SHORT).show();
            }
        });

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OpcoesActivity.class);
                startActivity(intent);
            }
        });

        upBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FactoryQuestion factory = new FactoryQuestion();
                factory.SetQuestionFire(factory.questionExperimental);
                factory.SetQuestionFire(factory.questionTestando);
                factory.SetQuestionFire(factory.questionTestando1);
                factory.SetQuestionFire(factory.questionTestando2);
                factory.SetQuestionFire(factory.questionTestando3);
                factory.SetQuestionFire(factory.questionTestando4);
                factory.SetQuestionFire(factory.questionTestando5);
                factory.SetQuestionFire(factory.questionTestando6);
                factory.SetQuestionFire(factory.questionTestando7);
                factory.SetQuestionFire(factory.questionTestando8);
                factory.SetQuestionFire(factory.questionTestando9);
                factory.SetQuestionFire(factory.questionTestando10);


            }
        });
    }
}
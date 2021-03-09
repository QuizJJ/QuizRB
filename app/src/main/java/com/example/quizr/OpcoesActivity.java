package com.example.quizr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class OpcoesActivity extends AppCompatActivity {

    private MaterialButton futebol;
    private MaterialButton conhecimentos;
    private MaterialButton historia;
    private MaterialButton filmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_opcoes);

        futebol = findViewById(R.id.button_jogar_futebol);
        conhecimentos = findViewById(R.id.button_jogar_conhecimentos_gerais);
        historia = findViewById(R.id.button_jogar_Historia);
        filmes = findViewById(R.id.button_jogar_filmes);



        futebol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirQuzi =  new Intent(getApplicationContext(),QuizActivity.class);

                abrirQuzi.putExtra("QuizOption","Futebol");
                startActivity(abrirQuzi);

            }
        });

        conhecimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirQuzi =  new Intent(getApplicationContext(),QuizActivity.class);

                abrirQuzi.putExtra("QuizOption","Conhecimentos");
                startActivity(abrirQuzi);

            }
        });

        historia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirQuzi =  new Intent(getApplicationContext(),QuizActivity.class);

                abrirQuzi.putExtra("QuizOption","Historia");
                startActivity(abrirQuzi);
            }
        });

        filmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirQuzi =  new Intent(getApplicationContext(),QuizActivity.class);
                abrirQuzi.putExtra("QuizOption","Filme");
                startActivity(abrirQuzi);
            }
        });
        }

    }

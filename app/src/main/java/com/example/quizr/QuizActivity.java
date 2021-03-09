package com.example.quizr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {



    private TextView enumciadoQuestao;
    private TextView textOp1;
    private TextView textOp2;
    private TextView textOp3;
    private TextView textOp4;
    private int idQuestaoCerta;

    private CardView cardError;
    private CardView cardSucess;
    private String tema;
    private int idQuestao;
    private int contador = 0;

    private String recebeTEma;
    private int pontosAnteriores;
    private int jogosAnteriores;
    private int conversor = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_quiz);

        Bundle extras  = getIntent().getExtras();

        recebeTEma = extras.getString("QuizOption");
        Log.d("VERIFICATEEEMA",recebeTEma);


        enumciadoQuestao = findViewById(R.id.enum_question);
        cardError = findViewById(R.id.card_failure);
        cardSucess = findViewById(R.id.card_sucesso);
        textOp1 = findViewById(R.id.op1);
        textOp2 = findViewById(R.id.op2);
        textOp3 = findViewById(R.id.op3);
        textOp4 = findViewById(R.id.op4);



        //numero de ideias cadastradas em cada Tema,(O numero é pego pelo id na factory atualizar de acorodo quando fora dicionado), o JAva conta o )
        if(recebeTEma.equals("Futebol")){
            conversor = 8;
        }else if(recebeTEma.equals("Filme")){
            conversor = 2;
        }else if(recebeTEma == "Historia"){
            conversor = 0;
        }else if(recebeTEma == "Conhecimentos"){
            conversor = 0;
        }

        //funcao que ja comeca setando uma pergunta do tema na tela
        QuizItensFunction();

        Log.d("TESTETEXIII",textOp1.getId()+"/"+textOp2.getId()+"/"+textOp3.getId()+"/"+textOp4.getId()+"/");


        FirebaseAuth user = FirebaseAuth.getInstance();
        FirebaseFirestore instaceBank = FirebaseFirestore.getInstance();

        textOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ConferindoOResultado(idQuestaoCerta,textOp1.getId());

                if (contador==6){
                    adicionaJogos(jogosAnteriores);
                    Intent intent = new Intent(getApplicationContext(),OpcoesActivity.class);
                    startActivity(intent);
                    Toast.makeText(QuizActivity.this, "Obrigado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConferindoOResultado(idQuestaoCerta,textOp2.getId());

                if (contador==6){
                    adicionaJogos(jogosAnteriores);
                    Intent intent = new Intent(getApplicationContext(),OpcoesActivity.class);
                    startActivity(intent);
                    Toast.makeText(QuizActivity.this, "Obrigado", Toast.LENGTH_SHORT).show();

                }

            }
        });

        textOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConferindoOResultado(idQuestaoCerta,textOp3.getId());

                if (contador==6){
                    adicionaJogos(jogosAnteriores);
                    Intent intent = new Intent(getApplicationContext(),OpcoesActivity.class);
                    startActivity(intent);
                    Toast.makeText(QuizActivity.this, "Obrigado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConferindoOResultado(idQuestaoCerta,textOp4.getId());

                if (contador==6){
                    adicionaJogos(jogosAnteriores);
                    Intent intent = new Intent(getApplicationContext(),OpcoesActivity.class);
                    startActivity(intent);
                    Toast.makeText(QuizActivity.this, "Obrigado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void ConferindoOResultado(int idCorrectionQuestion, int idTextREsposta) {

        if (idCorrectionQuestion == 1 && idTextREsposta == 2131231022) {

            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                DisplaySucess();
            QuizItensFunction();

        } else if (idCorrectionQuestion == 2 && idTextREsposta == 2131231023) {

            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            DisplaySucess();
            QuizItensFunction();

        } else if (idCorrectionQuestion == 3 && idTextREsposta == 2131231024) {

            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            DisplaySucess();
            QuizItensFunction();

        } else if (idCorrectionQuestion == 4 && idTextREsposta == 2131231025) {

            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            DisplaySucess();
            QuizItensFunction();

            FirebaseAuth user = FirebaseAuth.getInstance();
            FirebaseFirestore fb = FirebaseFirestore.getInstance();
            fb.collection("Users").document(user.getCurrentUser().getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot dc = task.getResult();
                                pontosAnteriores = dc.toObject(ModelUser.class).getPontos();
                                jogosAnteriores = dc.toObject(ModelUser.class).getNumeroJogos();

                                FirebaseAuth user = FirebaseAuth.getInstance();
                                FirebaseFirestore fb = FirebaseFirestore.getInstance();
                                DocumentReference userDocument = fb.collection("Users").document(user.getCurrentUser().getUid());
                                userDocument.update("pontos",100+pontosAnteriores);
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });


        } else {

            Toast.makeText(this, "Errou :/", Toast.LENGTH_SHORT).show();
            DisplayError();
            //metodo que busca a pergunta
            QuizItensFunction();
        }
    }


    //deixa a arte de ERROR visivel por um periodo
    public void DisplayError(){

            cardError.setVisibility(View.VISIBLE);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                        cardError.setVisibility(View.GONE);
                    }
                }, 3000);
           }

           //deixa a arte de sucesso visivel por um periodo
    public void DisplaySucess(){

        cardSucess.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cardSucess.setVisibility(View.GONE);
            }
        }, 3000);
    }


    //pesquisa ideia do banco e seta os valores na tela
    public void QuizItensFunction() {

        FirebaseAuth user = FirebaseAuth.getInstance();
        FirebaseFirestore instaceBank = FirebaseFirestore.getInstance();


        Log.d("CONVERSOR",conversor+"//");
        //sortei um numero random e pega o numero sorteado equivalente a questao do banco
        Random random = new Random();
        int id = random.nextInt(conversor);

        //contador para restringar quantas vezes o jogodaor jogou no memso modo
        contador++;

        instaceBank.collection("Questions").document(recebeTEma).collection(recebeTEma).document(Integer.toString(id)).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();

                            enumciadoQuestao.setText(document.toObject(QuestionClass.class).getName());
                            textOp1.setText(document.toObject(QuestionClass.class).getOp1());
                            textOp2.setText(document.toObject(QuestionClass.class).getOp2());
                            textOp3.setText(document.toObject(QuestionClass.class).getOp3());
                            textOp4.setText(document.toObject(QuestionClass.class).getOp4());
                            tema = (document.toObject(QuestionClass.class).getTema());
                            idQuestao = (document.toObject(QuestionClass.class).getId());
                            idQuestaoCerta = (document.toObject(QuestionClass.class).getCorrectOption());

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(QuizActivity.this, "Documento não encontrado", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void adicionaJogos(int inteiro){
        FirebaseAuth user = FirebaseAuth.getInstance();
        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        DocumentReference userDocument = fb.collection("Users").document(user.getCurrentUser().getUid());
        userDocument.update("numeroJogos",1+inteiro);
    }
}
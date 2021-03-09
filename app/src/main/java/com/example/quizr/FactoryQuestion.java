package com.example.quizr;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FactoryQuestion {

    List<QuestionClass> questionClasses;

    public QuestionClass questionExperimental = new QuestionClass(1,"nomeSegundaQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);
    public QuestionClass questionTestando = new QuestionClass(0,"nomePrimeiroQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);
    public QuestionClass questionTestando1 = new QuestionClass(1,"nomeTerceiraQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);
    public QuestionClass questionTestando2 = new QuestionClass(2,"nomeQuartaQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);
    public QuestionClass questionTestando3 = new QuestionClass(3,"nomeQuintaQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);
    public QuestionClass questionTestando4 = new QuestionClass(4,"nomeSextaQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);
    public QuestionClass questionTestando5 = new QuestionClass(5,"nomeSetimaQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);
    public QuestionClass questionTestando6 = new QuestionClass(6,"nomeOitavaQuestao","Futebol","OpcaoTEste1","OpcaoTEste2","OpcaoTEste3","OpcaoTEste4",4);

    public QuestionClass questionTestando7 = new QuestionClass(7,"Maria","Futebol","MARIA1","MARIA2","MARIA3","MARIA4",4);

    public QuestionClass questionTestando8 = new QuestionClass(0,"Maria","Filme","MARIA1","MARIA2","MARIA3","MARIA4",2);
    public QuestionClass questionTestando9 = new QuestionClass(1,"FIlme1","Filme","MARIA1","MARIA2","MARIA3","MARIA4",2);
    public QuestionClass questionTestando10 = new QuestionClass(2,"FIlme2","Filme","MARIA1","MARIA2","MARIA3","MARIA4",2);
    //metodo que seta as questoes no banco
    public void SetQuestionFire(QuestionClass question){

        FirebaseFirestore fire = FirebaseFirestore.getInstance();

        fire.collection("Questions").document(question.tema).collection(question.tema).document(Integer.toString(question.id)).set(question)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("LOGDESUCESSOFACTORY", "SUCESSS");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("LOGDEFALHA", "FALHA");
            }
        });
    }
}
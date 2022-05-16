package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int correctAnsLocation,score=0,noQues=0;
    Button goButton,button0,button1,button2,button3,playAgainButton;
    TextView sumTextView,answerTextView,scoreTextView,timerTextView;
    ConstraintLayout gameLayout;


    ArrayList<Integer> ans = new ArrayList<Integer>();

    public void chooseAnswer(View view){
       if( Integer.toString(correctAnsLocation).equals(view.getTag().toString())){
           answerTextView.setText("Correct!");
           score++;
       }
       else {
           answerTextView.setText("Wrong:(");
       }
       noQues++;
       scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noQues));

       newQues();
    }

    public void newQues(){
        Random random = new Random();
        int a=random.nextInt(51),b=random.nextInt(51);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        correctAnsLocation=random.nextInt(4);
        ans.clear();

        for (int i=0;i<4;i++){
            if(i==correctAnsLocation){
                ans.add(a+b);

            }
            else{
                int wrongAns=random.nextInt(101);
                while(wrongAns==(a+b)){
                    wrongAns=random.nextInt(101);
                }
                ans.add(wrongAns );

            }
        }

        button0.setText(Integer.toString(ans.get(0)));
        button1.setText(Integer.toString(ans.get(1)));
        button2.setText(Integer.toString(ans.get(2)));
        button3.setText(Integer.toString(ans.get(3)));
    }

    public void playAgain(View view){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score=0;
        noQues=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noQues));
        newQues();
        playAgainButton.setVisibility(View.INVISIBLE);
        answerTextView.setText("");

        new CountDownTimer(30050, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Integer.toString((int)(millisUntilFinished/1000))+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                answerTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }


    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView=findViewById(R.id.sumTextView);
        answerTextView=findViewById(R.id.answerTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);

        goButton=findViewById(R.id.goButton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgainButton=findViewById(R.id.playAgainButton);

        gameLayout=findViewById(R.id.gameLayout);

        gameLayout.setVisibility(View.INVISIBLE);

        goButton.setVisibility(View.VISIBLE);

    }
}
package com.example.n9420924.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startBtn);
        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);

        //Four result buttons
        Button btn0 = (Button) findViewById(R.id.button);
        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        // create 4 random number, where one is correct answer
        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;

        for (int i=0 ; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                //make sure incorrectAnswer must be different with correct Answer
                while(incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

    btn0.setText(Integer.toString(answers.get(0)));
    btn1.setText(Integer.toString(answers.get(1)));
    btn2.setText(Integer.toString(answers.get(2)));
    btn3.setText(Integer.toString(answers.get(3)));







    }
}

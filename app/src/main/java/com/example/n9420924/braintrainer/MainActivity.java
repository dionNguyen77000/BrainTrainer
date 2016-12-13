package com.example.n9420924.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //
    RelativeLayout gameRelativeLayout;

    //Button Area
    Button startButton;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgainBtn;

    TextView sumTextView;
    TextView resultTextView;
    TextView pointTextView;
    TextView timerTextView;


    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0 ;
    int numberOfQuestions = 0;

    public void disableButtonClick(){
        btn0 = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn0.setEnabled(false);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
    }

    public void enableButtonClick(){
        btn0 = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
    }



    public void playAgainClick(View v) {
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);

        enableButtonClick();

        generateQuestion();

        //count down timer
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText((String.valueOf(l/1000)) + "s");
            }

            @Override
            public void onFinish() {
                playAgainBtn.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: " + pointTextView.getText());
                disableButtonClick();
            }
        }.start();

    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        // create 4 random number, where one is correct answer
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();
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

    public void chooseAnswer(View v) {
        //Log.i("Tag", (String) v.getTag());
        if(v.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            //Log.i("Correct", "correct");
            score ++;
            resultTextView.setText(("Correct!"));
        } else {
            resultTextView.setText("Incorrect!");
        }

        numberOfQuestions ++;
        pointTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

        generateQuestion();
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgainClick(findViewById(R.id.playAgainButton));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        startButton = (Button) findViewById(R.id.startBtn);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointTextView =  (TextView) findViewById(R.id.pointTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        //Four result buttons
        btn0 = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        playAgainBtn = (Button) findViewById(R.id.playAgainButton);
    }


}

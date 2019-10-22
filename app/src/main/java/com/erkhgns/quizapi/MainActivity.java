package com.erkhgns.quizapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.halcyon.squareprogressbar.SquareProgressBar;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IContractQuiz.quizView {


    TextView tv_score, tv_question;

    Button btnFalse, btnTrue;


    QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        initializeData();

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        quizViewModel.getInstanceOfView(this);

        observeData();


    }

    private void initializeData() {
        tv_score = findViewById(R.id.tv_score);

        tv_question = findViewById(R.id.tv_question);

        btnTrue = findViewById(R.id.btn_true);

        btnFalse = findViewById(R.id.btn_false);

        btnFalse.setOnClickListener(onClickListener);

        btnTrue.setOnClickListener(onClickListener);
    }

    private void observeData() {
        //observe question
        quizViewModel.getListOfQuestion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    tv_question.setText(s);
                }
            }
        });
        quizViewModel.getCurrentScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer!=null)
                    tv_score.setText("Score: " +integer);
                else
                    tv_score.setText("Score: 0");
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {

                case R.id.btn_true:
                    quizViewModel.onClickTrue();

                    break;
                case R.id.btn_false:
                    quizViewModel.onClickFalse();


                    break;
            }
            quizViewModel.onPickAnswer();

        }
    };


    @Override
    public void onFinishQuiz() {
        btnTrue.setVisibility(View.INVISIBLE);
        btnFalse.setVisibility(View.INVISIBLE);
        tv_question.setText("Game is over");

    }
}
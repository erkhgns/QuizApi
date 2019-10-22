package com.erkhgns.quizapi;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class QuizViewModel extends AndroidViewModel implements IContractQuiz.quizPresenter{

    private MutableLiveData<List<SendResponse.RData>> listOfQuestion = new MutableLiveData<>();

    private MutableLiveData<String> currentQuestion = new MutableLiveData<>();

    private QuizApiRepository repository;

    private int currenIndex = 0;

    private MutableLiveData<Integer> currentScore= new MutableLiveData<>();

    private IContractQuiz.quizView view;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        repository = new QuizApiRepository(application);
    }


     LiveData<String> getListOfQuestion(){
        repository.getTrueOrFalseQuestion(listOfQuestion , currentQuestion);
        return currentQuestion;
    }
     LiveData<Integer> getCurrentScore(){
        return currentScore;
    }

    public void getInstanceOfView(IContractQuiz.quizView view){
        this.view = view;

    }




    @Override
    public void onClickTrue() {
        if(currentObject().getCorrect_answer().equals(Constants.ANSWER_TRUE))
            incrementScore();
    }

    @Override
    public void onClickFalse() {
        if(currentObject().getCorrect_answer().equals(Constants.ANSWER_FALSE))
           incrementScore();
    }

    @Override
    public void onPickAnswer() {

        currenIndex++;

        currentQuestion.setValue(currentObject().getQuestion());

        if(currenIndex == listOfQuestion.getValue().size()-1){
            view.onFinishQuiz();
        }

    }

    private SendResponse.RData currentObject(){
        return listOfQuestion.getValue().get(currenIndex);
    }

    private void incrementScore(){
        if(currentScore.getValue()!=null){
            currentScore.setValue(currentScore.getValue()+1);
        }else
            currentScore.setValue(1);
    }
}

package com.erkhgns.quizapi;

import java.util.List;

public interface IContractQuiz {

    interface quizPresenter{
        void onClickTrue();

        void onClickFalse();

        void onPickAnswer();
    }
    interface quizView{
        void onFinishQuiz();
    }


    interface responseListener{
        void onSuccess(List<SendResponse.RData> data);
    }



}

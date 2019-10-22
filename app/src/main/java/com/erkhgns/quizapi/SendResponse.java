package com.erkhgns.quizapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendResponse {

    @Expose
    @SerializedName("response_code")
    private int response_code;


    @Expose
    @SerializedName("results")
    private List<RData> data;

    public int getResponse_code() {
        return response_code;
    }

    public List<RData> getData() {
        return data;
    }

    public class RData {

        private String category;

        private String type;

        private String difficulty;

        private String question;

        private String correct_answer;

        private List<String> incorrect_answers;


        public String getCategory() {
            return category;
        }


        public String getType() {
            return type;
        }


        public String getDifficulty() {
            return difficulty;
        }


        public String getQuestion() {
            return question;
        }


        public String getCorrect_answer() {
            return correct_answer;
        }


        public List<String> getIncorrect_answer() {
            return incorrect_answers;
        }


    }

}
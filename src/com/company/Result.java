package com.company;

public enum Result {

    X_WINS("X wins"),
    O_WINS("O wins"),
    DRAW("Draw");

    private String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}

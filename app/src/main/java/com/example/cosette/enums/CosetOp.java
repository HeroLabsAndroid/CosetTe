package com.example.cosette.enums;

public enum CosetOp {
    PLUS(0), MINUS(1), MULT(2), DIV(3);

    int id;

    private CosetOp(int opid) {
        id = opid;
    }
}

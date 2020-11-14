package com.example.cosette.databundles;

import android.text.Html;

import com.example.cosette.enums.CosetOp;

public class CalcHistoryData {
    int nr1, nr2, dim, res;
    CosetOp op;

    public boolean valid() {
        return ( (nr1 > -1) && (nr2 > -1) && (dim > -1) && (res > -1) && (op != null) );
    }

    public CalcHistoryData(int nr1, int nr2, int dim, int res, CosetOp op) {
        this.nr1 = nr1;
        this.nr2 = nr2;
        this.dim = dim;
        this.res = res;
        this.op = op;
    }

    public int getNr1() {
        return nr1;
    }

    public CalcHistoryData setNr1(int nr1) {
        this.nr1 = nr1;
        return this;
    }

    public int getNr2() {
        return nr2;
    }

    public CalcHistoryData setNr2(int nr2) {
        this.nr2 = nr2;
        return this;
    }

    public int getDim() {
        return dim;
    }

    public CalcHistoryData setDim(int dim) {
        this.dim = dim;
        return this;
    }

    public int getRes() {
        return res;
    }

    public CalcHistoryData setRes(int res) {
        this.res = res;
        return this;
    }

    public CosetOp getOp() {
        return op;
    }

    public CalcHistoryData setOp(CosetOp op) {
        this.op = op;
        return this;
    }

    @Override
    public String toString() {
        return Html.fromHtml("["+res+"]<sup>"+dim+"</sup> = "+nr1+" "+op+" "+nr2).toString();
    }
}

package com.example.cosette.databundles;

import android.content.res.Resources;

import com.example.cosette.R;
import com.example.cosette.enums.CosetOp;

public class CosetCalcData {
    int dim;
    int nr1, nr2;

    public CosetCalcData(int dim, int nr1, int nr2) {
        this.dim = dim;
        this.nr1 = nr1;
        this.nr2 = nr2;
    }

    public int getDim() {
        return dim;
    }

    public CosetCalcData setDim(int dim) {
        this.dim = dim;
        return this;
    }

    public int getNr1() {
        return nr1;
    }

    public CosetCalcData setNr1(int nr1) {
        this.nr1 = nr1;
        return this;
    }

    public int getNr2() {
        return nr2;
    }

    public CosetCalcData setNr2(int nr2) {
        this.nr2 = nr2;
        return this;
    }
}

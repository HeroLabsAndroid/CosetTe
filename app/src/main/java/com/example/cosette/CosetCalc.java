package com.example.cosette;

import android.content.res.Resources;
import android.media.VolumeShaper;

import androidx.annotation.Nullable;

import com.example.cosette.databundles.CalcHistoryData;
import com.example.cosette.databundles.CosetCalcData;
import com.example.cosette.enums.CosetOp;

public class CosetCalc {
    CosetCalcData ccd;
    OutputInterface oI;

    int lastres;
    int[] ogNrs = new int[2];

    CosetOp lastOp;



    public CosetCalc(CosetCalcData data, OutputInterface oI) {
        this.oI = oI;
        this.ccd = data;
        ccd.setNr1(mod(data.getNr1(), data.getDim()));
        ccd.setNr2(mod(data.getNr2(), data.getDim()));
        ogNrs[0] = nr1();
        ogNrs[1] = nr2();


    }

    public CosetCalc updateData (CosetCalcData ccd) {
        oI.pushHistory(pushToHistory());
        this.ccd = ccd;
        return this;
    }

    private CalcHistoryData pushToHistory() {
        CalcHistoryData hisD = new CalcHistoryData(ogNrs[0], ogNrs[1], dim(), lastres, lastOp);
        return hisD;
    }

    private static int mod(int i, int m) {
        int n = (int) (i/m);
        return i - n*m;
    }

    private int dim() {
        return ccd.getDim();
    }
    private int nr1() {
        return ccd.getNr1();
    }
    private int nr2() {
        return ccd.getNr2();
    }


    public int apply(CosetOp op) {
        int res = 0;
        switch(op){
            case PLUS: {
                res = nr1() + nr2();
                break;
            }
            case MULT: {
                res = nr1() * nr2();
                break;
            }
            default: {
                res = -1;
                break;
            }
        }

        res = mod(res, dim());
        lastres = res;

        lastOp = op;

        oI.outputRes(res);
        return res;
    }

}

package com.example.cosette;

import com.example.cosette.databundles.CalcHistoryData;

public interface OutputInterface {
    void outputRes(int out);
    void outputInfo(String txt);
    String getInfo();
    void pushHistory(CalcHistoryData historyData);
}

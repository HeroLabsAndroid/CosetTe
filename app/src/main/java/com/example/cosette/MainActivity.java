package com.example.cosette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cosette.backend.HistoryAdapter;
import com.example.cosette.backend.OverrideValueInterface;
import com.example.cosette.databundles.CalcHistoryData;
import com.example.cosette.databundles.CosetCalcData;
import com.example.cosette.enums.CosetOp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OutputInterface, OverrideValueInterface {
    RecyclerView historyRV;

    EditText resET;
    EditText nr1ET;
    EditText nr2ET;

    TextView outTV;
    TextView detTV;

    FloatingActionButton plusFAB;
    FloatingActionButton multFAB;

    CosetCalc calc;

    ArrayList<CalcHistoryData> historyData = new ArrayList<CalcHistoryData>();
    HistoryAdapter histAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLayoutReferences();
        setupCalculator();
        setupHistory();
    }

    private CalcHistoryData[] histArr() {
        CalcHistoryData[] arr = new CalcHistoryData[historyData.size()];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = historyData.get(arr.length-(i+1));
        }

        return arr;
    }

    private void setupHistory() {
        historyRV.setHasFixedSize(true);
        historyRV.setLayoutManager(new LinearLayoutManager(this));

        histAdapt = new HistoryAdapter(histArr(), this);
        historyRV.setAdapter(histAdapt);
    }

    private void setupCalculator() {
        calc = new CosetCalc(valuesFromLayout(), this);

        plusFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.updateData(valuesFromLayout());
                calc.apply(CosetOp.PLUS);
            }
        });
        multFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.updateData(valuesFromLayout());
                calc.apply(CosetOp.MULT);
            }
        });
    }

    private CosetCalcData valuesFromLayout() {
        int dim = Integer.parseInt(resET.getText().toString());
        int nr1 = Integer.parseInt(nr1ET.getText().toString());
        int nr2 = Integer.parseInt(nr2ET.getText().toString());

        CosetCalcData ccd = new CosetCalcData(dim, nr1, nr2);
        return ccd;
    }

    private void getLayoutReferences() {
        resET = findViewById(R.id.resclassET);
        nr1ET = findViewById(R.id.number1ET);
        nr2ET = findViewById(R.id.number2ET);

        outTV = findViewById(R.id.outputTV);
        detTV = findViewById(R.id.detailTV);

        plusFAB = findViewById(R.id.plusFAB);
        multFAB = findViewById(R.id.multFAB);

        historyRV = findViewById(R.id.historyRV);
    }

    @Override
    public void outputRes(int out) {
        outTV.setText(""+out);
    }

    @Override
    public void outputInfo(String txt) {
        detTV.setText(txt);
    }

    @Override
    public String getInfo() {
        return detTV.getText().toString();
    }

    @Override
    public void pushHistory(CalcHistoryData hisD) {
        if(hisD.valid()) {
            Log.d("MAIN", "attempting to push item to history");
            historyData.add(hisD);

            if(historyData.size() > 16) {
                historyData.remove(0);
            }
            histAdapt = (HistoryAdapter) historyRV.getAdapter();
            histAdapt.setDataSet(histArr());
            historyRV.setAdapter(histAdapt);

            historyRV.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void setFirst(int i) {
        nr1ET.setText(""+i);
        calc.updateData(valuesFromLayout());
    }

    @Override
    public void setSecond(int i) {
        nr2ET.setText(""+i);
        calc.updateData(valuesFromLayout());
    }
}
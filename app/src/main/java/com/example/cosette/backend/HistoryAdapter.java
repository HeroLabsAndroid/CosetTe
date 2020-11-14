package com.example.cosette.backend;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosette.OutputInterface;
import com.example.cosette.R;
import com.example.cosette.databundles.CalcHistoryData;
import com.example.cosette.databundles.CosetCalcData;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.historyVH> implements OutputInterface {

    CalcHistoryData[] data;
    OverrideValueInterface ovi;

    public HistoryAdapter(CalcHistoryData[] data, OverrideValueInterface ovi) {
        this.data = data;
        this.ovi = ovi;
    }

    public void setDataSet(CalcHistoryData[] d) {
        data = d;
    }

    @Override
    public historyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout itm = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hist_item, parent, false);

        historyVH vh = new historyVH(itm);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull historyVH holder, int position) {
        holder.setData(data[position]);
        Log.d("HistAdapt", "pushing item with res="+data[position].getRes());

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public void outputRes(int out) {

    }

    @Override
    public void outputInfo(String txt) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void pushHistory(CalcHistoryData historyData) {

    }

    public class historyVH extends RecyclerView.ViewHolder {

        CalcHistoryData histItm;
        String txt;

        Button set1Btn, set2Btn;
        EditText eqnET;

        public historyVH(LinearLayout itm) {
            super(itm);

            eqnET = itm.findViewById(R.id.lastResET);

            set1Btn = itm.findViewById(R.id.set1btn);
            set1Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ovi.setFirst(histItm.getNr1());
                }
            });

            set2Btn = itm.findViewById(R.id.set2btn);
            set2Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ovi.setSecond(histItm.getNr2());
                }
            });
        }

        public void setData(CalcHistoryData chd) {
            histItm = chd;
            txt = chd.toString();
            eqnET.setText(txt);
        }
    }
}

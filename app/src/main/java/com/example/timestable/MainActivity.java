package com.example.timestable;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myListView;

    public void generateTimesTable(int number){
        ArrayList<String> table = new ArrayList<String>();
        for(int i=1;i<=10;i++)
            table.add(Integer.toString(number*i));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,table);
        myListView.setAdapter(arrayAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = findViewById(R.id.myListView);
        final SeekBar numberControl = findViewById(R.id.mySeekBar);

        final int min = 1;
        int max = 20;
        int startingNumber = 10;
        numberControl.setMax(max);
        numberControl.setProgress(startingNumber);

        generateTimesTable(startingNumber);

        numberControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int finalNumber;

                if(progress < min){
                    finalNumber = min;
                    numberControl.setProgress(min);
                }else
                    finalNumber = progress;

                Log.i("Number selected => ",Integer.toString(progress+1));
                generateTimesTable(finalNumber);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

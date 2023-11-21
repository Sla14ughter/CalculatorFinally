package com.example.calculatorfinally;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        try {
            TextView resultView = findViewById(R.id.result);
            EditText
                    editWidth = findViewById(R.id.editWidth),
                    editHeight = findViewById(R.id.editHeight),
                    editConsumption = findViewById(R.id.editConsumption),
                    editLayers = findViewById(R.id.editLayers),
                    editVolume = findViewById(R.id.editVolume);
            RadioGroup reserveGroup = findViewById(R.id.reserve);
            String
                    widthString = editWidth.getText().toString(),
                    heightString = editHeight.getText().toString(),
                    consumptionString = editConsumption.getText().toString(),
                    layersString = editLayers.getText().toString(),
                    volumeString = editVolume.getText().toString();
            Double
                    width = Double.parseDouble(widthString),
                    height = Double.parseDouble(heightString),
                    consumption = Double.parseDouble(consumptionString),
                    volume = Double.parseDouble(volumeString),
                    reserve = (double) 0;
            int layers = Integer.parseInt(layersString), result;
            final int
                    checked = reserveGroup.getCheckedRadioButtonId(),
                    fifteen = R.id.fifteen,
                    ten = R.id.ten,
                    zero = R.id.zero;
            if (checked == fifteen)
                reserve = 1.15;
            else if (checked == ten)
                reserve = 1.10;
            else if (checked == zero)
                reserve = (double) 1;
            result = (int) Math.ceil(width * height * layers * reserve / (consumption * volume));
            resultView.setText(String.format(Locale.getDefault(), "Вам понадобится %d %s", result, formatJars(result)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatJars(int count) {
        if (count % 100 >= 11 && count % 100 <= 14) {
            return "банок";
        } else {
            switch (count % 10) {
                case 1:
                    return "банка";
                case 2:
                case 3:
                case 4:
                    return "банки";
                default:
                    return "банок";
            }
        }
    }
}
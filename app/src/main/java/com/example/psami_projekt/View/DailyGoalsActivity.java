package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.psami_projekt.R;

public class DailyGoalsActivity extends AppCompatActivity {

    private EditText editTextMaxKcal, editTextProteins, editTextFats, editTextCarbs;
    private EditText editTextPercentageProteins, editTextPercentageFats, editTextPercentageCarbs;
    private Button btnSave;
    private Integer maxKcal = 1000;
    private Integer proteins, fats, carbs, percentageProteins, percentageFats, percentageCarbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_goals);

        initViews();

        editTextProteins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                proteins = Integer.valueOf(editTextProteins.getText().toString());
                percentageProteins = proteins * 4 * 100 / maxKcal;
                editTextPercentageProteins.setText(percentageProteins.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        editTextFats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fats = Integer.valueOf(editTextFats.getText().toString());
                percentageFats = fats * 9 * 100 / maxKcal;
                editTextPercentageFats.setText(percentageFats.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        editTextCarbs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                carbs = Integer.valueOf(editTextCarbs.getText().toString());
                percentageCarbs = carbs * 4 * 100 / maxKcal;
                editTextPercentageCarbs.setText(percentageCarbs.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }

    private void initViews() {
        editTextMaxKcal = findViewById(R.id.editTextMaxKcal);
        editTextProteins = findViewById(R.id.editTextMaxProtein);
        editTextFats = findViewById(R.id.editTextMaxFats);
        editTextCarbs = findViewById(R.id.editTextMaxCarbs);
        editTextPercentageProteins = findViewById(R.id.editTextPercentProtein);
        editTextPercentageFats = findViewById(R.id.editTextPercentFat);
        editTextPercentageCarbs = findViewById(R.id.editTextPercentCarbs);
        btnSave = findViewById(R.id.btnSaveMax);
    }
}
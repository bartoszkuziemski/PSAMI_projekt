package com.example.psami_projekt.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.google.android.material.appbar.MaterialToolbar;

public class CalculatorActivity extends BaseActivity {

    private EditText editTextAge, editTextHeight, editTextWeight;
    private RadioGroup radioGroupGender, radioGroupGoals;
    private Spinner spinnerActivity;
    private Button btnCalculate, btnSave;
    private TextView txtWarning, txtCalculatedKcal;
    private Integer age, height, weight;
    private Double AMR;
    private MaterialToolbar toolbar;
    private final Utils utils = Utils.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_calculator, contentFrameLayout);

        initViews();
        super.setToolbar(this, toolbar, "Calculate BMR");
        setCalculateListener();
        setSaveListener();

    }

    private void setCalculateListener() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextAge.getText().toString().equals("") || editTextHeight.getText().toString().equals("") || editTextWeight.getText().toString().equals("")) {
                    txtWarning.setVisibility(View.VISIBLE);
                    txtCalculatedKcal.setVisibility(View.GONE);
                } else {
                    txtWarning.setVisibility(View.GONE);
                    txtCalculatedKcal.setVisibility(View.VISIBLE);
                    age = Integer.valueOf(editTextAge.getText().toString());
                    height = Integer.valueOf(editTextHeight.getText().toString());
                    weight = Integer.valueOf(editTextWeight.getText().toString());
                    calculateBMR();
                }
            }
        });
    }

    private void calculateBMR() {
        int genderCheckedButton = radioGroupGender.getCheckedRadioButtonId();
        Double BMR = 0.0;
        switch (genderCheckedButton) {
            case R.id.radioButtonMale:
                BMR = 66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
                break;
            case R.id.radioButtonFemale:
                BMR = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age);
                break;
            default:
                break;
        }
        calculateAMR(BMR);
    }

    private void calculateAMR(Double BMR) {
        int spinnerBMR = spinnerActivity.getSelectedItemPosition();
        switch (spinnerBMR) {
            case 0:
                AMR = BMR * 1.2;
                break;
            case 1:
                AMR = BMR * 1.375;
                break;
            case 2:
                AMR = BMR * 1.55;
                break;
            case 3:
                AMR = BMR * 1.725;
                break;
            case 4:
                AMR = BMR * 1.9;
                break;
            default:
                break;
        }
        int goalsCheckedButton = radioGroupGoals.getCheckedRadioButtonId();
        switch (goalsCheckedButton) {
            case R.id.radioButtonWeightLoss:
                AMR = AMR * 0.9;
                break;
            case R.id.radioButtonWeightGain:
                AMR = AMR * 1.1;
                break;
            default:
                break;
        }
        txtCalculatedKcal.setText("Calculated calories: " + String.valueOf(AMR.intValue()) + " kcal");
    }

    private void setSaveListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.setMaxKcal(AMR.intValue());
                Integer maxProteins = (int) (AMR / 4 * 0.5);
                Integer maxFats = (int) (AMR / 9 * 0.3);
                Integer maxCarbs = (int) (AMR / 4 * 0.2);
                utils.setMaxProteins(maxProteins);
                utils.setMaxFats(maxFats);
                utils.setMaxCarbs(maxCarbs);
            }
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.CalculatorToolbar);
        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        radioGroupGender = findViewById(R.id.radioGroup);
        spinnerActivity = findViewById(R.id.spinner);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtWarning = findViewById(R.id.txtCalculatorWarning);
        radioGroupGoals = findViewById(R.id.radioGroupGoals);
        txtCalculatedKcal = findViewById(R.id.txtCalculatedKcal);
        btnSave = findViewById(R.id.btnSaveCalculator);
    }
}
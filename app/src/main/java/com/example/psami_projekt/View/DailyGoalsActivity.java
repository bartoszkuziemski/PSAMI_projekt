package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;

import org.w3c.dom.Text;

public class DailyGoalsActivity extends AppCompatActivity {

    private EditText editTextMaxKcal, editTextProteins, editTextFats, editTextCarbs;
    private TextView txtPercentageProteins, txtPercentageFats, txtPercentageCarbs, txtPercentageTotal, txtWarning;
    private Button btnSave;
    private Integer maxKcal;
    private Integer maxProteins, maxFats, maxCarbs, percentageProteins = 0 , percentageFats = 0, percentageCarbs = 0, percentageTotal = 0;
    private Utils utils = Utils.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_goals);

        initViews();
        setFieldsFromUtils();

        setTextChangedListeners();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxKcal <=0 || maxProteins <= 0 || maxFats <= 0 || maxCarbs <= 0 || percentageTotal != 100) {
                    txtWarning.setVisibility(View.VISIBLE);
                } else {
                    utils.setMaxKcal(maxKcal);
                    utils.setMaxProteins(maxProteins);
                    utils.setMaxFats(maxFats);
                    utils.setMaxCarbs(maxCarbs);
                    txtWarning.setVisibility(View.GONE);
                    Toast.makeText(DailyGoalsActivity.this, "Settings saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void setTextChangedListeners() {
        editTextMaxKcal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                maxKcal = Integer.valueOf(editTextMaxKcal.getText().toString());
                calculateProteins();
                calculatePercentageTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        editTextProteins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateProteins();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        editTextFats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateFats();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        editTextCarbs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateCarbs();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private Integer calculatePercentage(Integer maxValue, Integer kcalInGram, TextView txtPercentage) {
        Integer percentage = maxValue * kcalInGram * 100 / maxKcal;
        txtPercentage.setText(percentage.toString());
        return percentage;
    }

    private void calculateProteins() {
        if (editTextProteins.getText().toString().equals("")) {
            maxProteins = 0;
        } else {
            maxProteins = Integer.valueOf(editTextProteins.getText().toString());
        }
        percentageProteins = maxProteins * 4 * 100 / maxKcal;
        txtPercentageProteins.setText(percentageProteins.toString());
        calculatePercentageTotal();
    }

    private void calculateFats() {
        maxFats = Integer.valueOf(editTextFats.getText().toString());
        percentageFats = maxFats * 9 * 100 / maxKcal;
        txtPercentageFats.setText(percentageFats.toString());
        calculatePercentageTotal();
    }

    private void calculateCarbs() {
        maxCarbs = Integer.valueOf(editTextCarbs.getText().toString());
        percentageCarbs = maxCarbs * 4 * 100 / maxKcal;
        txtPercentageCarbs.setText(percentageCarbs.toString());
        calculatePercentageTotal();
    }

    private void calculatePercentageTotal() {
        percentageTotal = percentageProteins + percentageFats + percentageCarbs;
        txtPercentageTotal.setText(percentageTotal.toString());
    }

    private void initViews() {
        editTextMaxKcal = findViewById(R.id.editTextMaxKcal);
        editTextProteins = findViewById(R.id.editTextMaxProtein);
        editTextFats = findViewById(R.id.editTextMaxFats);
        editTextCarbs = findViewById(R.id.editTextMaxCarbs);
        txtPercentageProteins = findViewById(R.id.txtPercentageProteins);
        txtPercentageFats = findViewById(R.id.txtPercentageFats);
        txtPercentageCarbs = findViewById(R.id.txtPercentageCarbs);
        txtPercentageTotal = findViewById(R.id.txtPercentageTotal);
        txtWarning = findViewById(R.id.txtTotalPercentageWarn);
        txtWarning.setVisibility(View.GONE);
        btnSave = findViewById(R.id.btnSaveMax);
    }

    private void setFieldsFromUtils() {
        maxKcal = utils.getMaxKcal();
        maxProteins = utils.getMaxProteins();
        maxFats = utils.getMaxFats();
        maxCarbs = utils.getMaxCarbs();
        editTextMaxKcal.setText(String.valueOf(maxKcal));
        editTextProteins.setText(String.valueOf(maxProteins));
        editTextFats.setText(String.valueOf(maxFats));
        editTextCarbs.setText(String.valueOf(maxCarbs));
    }
}
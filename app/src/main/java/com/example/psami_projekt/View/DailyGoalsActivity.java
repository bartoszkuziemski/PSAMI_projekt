package com.example.psami_projekt.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.google.android.material.appbar.MaterialToolbar;

import org.w3c.dom.Text;

import java.util.Objects;

public class DailyGoalsActivity extends BaseActivity {

    private EditText editTextMaxKcal, editTextProteins, editTextFats, editTextCarbs;
    private TextView txtPercentageProteins, txtPercentageFats, txtPercentageCarbs, txtPercentageTotal, txtWarning;
    private Button btnSave;
    private Integer maxKcal;
    private Integer maxProteins, maxFats, maxCarbs, percentageProteins = 0, percentageFats = 0, percentageCarbs = 0, percentageTotal = 0;
    private final Utils utils = Utils.getInstance(this);
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set FrameLayout with BaseActivity to include Drawer,                 previously: setContentView(R.layout.activity_daily_goals);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_daily_goals, contentFrameLayout);

        initViews();

        setToolbar();

        setFieldsFromUtils();

        setTextChangedListeners();

        setOnClickListeners();

    }

    private void setOnClickListeners() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxKcal <= 0 || maxProteins <= 0 || maxFats <= 0 || maxCarbs <= 0 || percentageTotal != 100) {
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

    /**
     * Set toolbar and drawer toggle
     * super.getDrawer comes from BaseActivity
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Daily goals");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, super.getDrawerLayout(), toolbar, R.string.drawer_open, R.string.drawer_closed);
        super.getDrawerLayout().addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    private void setTextChangedListeners() {
        editTextMaxKcal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                maxKcal = getMaxValue(editTextMaxKcal);
                percentageProteins = calculatePercentage(maxProteins, 4, txtPercentageProteins);
                percentageFats = calculatePercentage(maxFats, 9, txtPercentageFats);
                percentageCarbs = calculatePercentage(maxCarbs, 4, txtPercentageCarbs);
                calculatePercentageTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editTextProteins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                maxProteins = getMaxValue(editTextProteins);
                percentageProteins = calculatePercentage(maxProteins, 4, txtPercentageProteins);
                calculatePercentageTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editTextFats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                maxFats = getMaxValue(editTextFats);
                percentageFats = calculatePercentage(maxFats, 9, txtPercentageFats);
                calculatePercentageTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editTextCarbs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                maxCarbs = getMaxValue(editTextCarbs);
                percentageCarbs = calculatePercentage(maxCarbs, 4, txtPercentageCarbs);
                calculatePercentageTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private Integer getMaxValue(EditText editText) {
        Integer maxValue;
        if (editText.getText().toString().equals("")){
        if (editText.getText().toString().equals("")) {
            maxValue = 1;
        } else {
            maxValue = Integer.valueOf(editText.getText().toString());
        }
        return maxValue;
    }

    private Integer calculatePercentage(Integer maxValue, Integer kcalInGram, TextView txtPercentage) {
        Integer percentage = maxValue * kcalInGram * 100 / maxKcal;
        txtPercentage.setText(percentage.toString());
        return percentage;
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
        toolbar = findViewById(R.id.dailyGoalsToolbar);
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
        percentageProteins = calculatePercentage(maxProteins, 4, txtPercentageProteins);
        percentageFats = calculatePercentage(maxFats, 9, txtPercentageFats);
        percentageCarbs = calculatePercentage(maxCarbs, 4, txtPercentageCarbs);
        calculatePercentageTotal();
    }
}
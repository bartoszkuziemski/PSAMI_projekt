package com.example.psami_projekt.View.MainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.psami_projekt.Model.Utils;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.BaseActivity;
import com.example.psami_projekt.View.CalendarActivity;

import java.time.LocalDate;

public class MainActivity extends BaseActivity {

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set FrameLayout with BaseActivity to include Drawer,                 previously: setContentView(R.layout.activity_main);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        getDateFromCalendar();

        Utils.getInstance(this);

        BottomKcalFragment bottomKcalFragment = new BottomKcalFragment();
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.bottomFragmentContainerView, bottomKcalFragment, null)
                .commit();

        MainFragment mainFragment = new MainFragment(bottomKcalFragment);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.mainFragmentContainerView, mainFragment, null)
                .commit();

        Bundle bundle = new Bundle();
        bundle.putString(CalendarActivity.DATE_ID_KEY, date);
        TopFragment topFragment = new TopFragment();
        topFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.topFragmentContainerView, topFragment, null)
                .commit();

    }

    /**
     * Get date from calendar, if there is none set today's date
     */
    private void getDateFromCalendar() {
        Intent intent = getIntent();
        if (intent.getStringExtra(CalendarActivity.DATE_ID_KEY) != null) {
            date = intent.getStringExtra(CalendarActivity.DATE_ID_KEY);
        } else {
            date = LocalDate.now().toString();
        }
        Utils.setDate(date);
    }
}
package com.example.psami_projekt.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.CalendarAdapter;
import com.google.android.material.appbar.MaterialToolbar;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class CalendarActivity extends BaseActivity {

    public static final String DATE_ID_KEY = "dateId";
    private RecyclerView calendarRecView;
    private CalendarAdapter calendarAdapter;
    private TextView txtMonthAndYear;
    private ImageButton btnPreviousMonth, btnNextMonth;
    private LocalDate currentDate;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_calendar, contentFrameLayout);

        currentDate = LocalDate.now();

        initViews();
        setToolbar();
        initRecyclerView();
        setMonthDays();
        setOnClickListeners();

        calendarAdapter.setDays(getDaysInMonth());

    }

    /**
     * Set toolbar and drawer toggle
     * super.getDrawer comes from BaseActivity
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Choose date");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, super.getDrawerLayout(), toolbar, R.string.drawer_open, R.string.drawer_closed);
        super.getDrawerLayout().addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void setOnClickListeners() {
        btnNextMonth.setOnClickListener(view -> nextMonth());
        btnPreviousMonth.setOnClickListener(view -> previousMonth());
    }

    private void setMonthDays() {
        txtMonthAndYear.setText(getMonthAndYear(currentDate));
    }

    private void nextMonth() {
        currentDate = currentDate.plusMonths(1);
        calendarAdapter.setDays(getDaysInMonth());
        setMonthDays();
    }

    private void previousMonth() {
        currentDate = currentDate.minusMonths(1);
        calendarAdapter.setDays(getDaysInMonth());
        setMonthDays();
    }

    private ArrayList<LocalDate> getDaysInMonth() {
        ArrayList<LocalDate> daysInMonth = new ArrayList<>();

        LocalDate firstOfMonth = currentDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            daysInMonth.add(firstOfMonth.plusDays(-dayOfWeek+i));
        }
        return daysInMonth;
    }

    private String getMonthAndYear(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initRecyclerView() {
        calendarAdapter = new CalendarAdapter(this);
        calendarRecView.setAdapter(calendarAdapter);
        calendarRecView.setLayoutManager(new GridLayoutManager(this, 7));
    }

    private void initViews() {
        calendarRecView = findViewById(R.id.calendarRecyclerView);
        txtMonthAndYear = findViewById(R.id.txtCalendarMonth);
        btnNextMonth = findViewById(R.id.btnCalendarNextMonth);
        btnPreviousMonth = findViewById(R.id.btnCalendarPreviousMonth);
        toolbar = findViewById(R.id.calendarToolbar);
    }

}
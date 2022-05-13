package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.CalendarAdapter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {

    private RecyclerView calendarRecView;
    private CalendarAdapter calendarAdapter;
    private TextView txtMonthAndYear;
    private ImageButton btnPreviousMonth, btnNextMonth;
    LocalDate currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        currentDate = LocalDate.now();
        initViews();
        initRecyclerView();

        setMonthDays();
        //calendarAdapter.setDaysOfMonth(getDaysInMonth(currentDate));
    }

    private void setMonthDays() {
        txtMonthAndYear.setText(getMonthAndYear(currentDate));
    }

    private ArrayList<String> getDaysInMonth(LocalDate date) {
        ArrayList<String> daysInMonth = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int numberOfDays = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = currentDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > numberOfDays + dayOfWeek) {
                daysInMonth.add("");
            } else {
                daysInMonth.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonth;
    }

    private String getMonthAndYear(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initRecyclerView() {
        calendarAdapter = new CalendarAdapter(getDaysInMonth(currentDate), this);
        calendarRecView.setAdapter(calendarAdapter);
        calendarRecView.setLayoutManager(new GridLayoutManager(this, 7));
    }

    private void initViews() {
        calendarRecView = findViewById(R.id.calendarRecyclerView);
        txtMonthAndYear = findViewById(R.id.txtCalendarMonth);
        btnNextMonth = findViewById(R.id.btnCalendarNextMonth);
        btnPreviousMonth = findViewById(R.id.btnCalendarPreviousMonth);
    }

}
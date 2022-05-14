package com.example.psami_projekt.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.psami_projekt.Model.Meal;
import com.example.psami_projekt.Model.MealDatabaseHelper;
import com.example.psami_projekt.R;
import com.example.psami_projekt.View.Adapter.CalendarAdapter;
import com.example.psami_projekt.View.Adapter.MealAdapter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {

    public static final String DAY_ID_KEY = "dayId";
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

        ArrayList<LocalDate> days = getDaysInMonth();
        if (days != null) {
            calendarAdapter.setDays(days);
        }

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextMonth();
            }
        });
        btnPreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousMonth();
            }
        });

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
        YearMonth yearMonth = YearMonth.from(currentDate);

        int numberOfDays = yearMonth.lengthOfMonth();
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
    }

}
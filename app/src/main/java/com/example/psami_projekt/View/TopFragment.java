package com.example.psami_projekt.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.psami_projekt.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class TopFragment extends Fragment {

    private TextView txtMonday, txtTuesday, txtWednesday, txtThursday, txtFriday, txtSaturday, txtSunday, txtYearMonth;
    private ImageView circleMonday, circleTuesday, circleWednesday, circleThursday, circleFriday, circleSaturday, circleSunday;
    private ImageButton btnPreviousWeek, btnNextWeek;
    private LocalDate chosenDate;
    private Integer dayOfWeek;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        initViews(view);
        setChosenDate();
        setImageViewsVisibility();
        setTextViews();
        setButtonsListeners();

        return view;
    }

    private void setButtonsListeners() {
        btnPreviousWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newDate = chosenDate.minusDays(dayOfWeek).toString();
                startNewActivity(newDate);
            }
        });
        btnNextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newDate = chosenDate.plusDays(7 - dayOfWeek + 1).toString();
                startNewActivity(newDate);
            }
        });
    }

    /**
     * Set array of days to display at the top
     * Set textViews for all 7 days
     * Set onClickListeners for all 7 days
     * Send date by intent to MainActivity
     */
    private void setTextViews() {
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            dates.add(chosenDate.plusDays(i - dayOfWeek));
        }

        ArrayList<TextView> textViews = new ArrayList<>(Arrays.asList(txtMonday, txtTuesday, txtWednesday, txtThursday, txtFriday, txtSaturday, txtSunday));
        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setText(dates.get(i).format(DateTimeFormatter.ofPattern("d")));
            int finalI = i;
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newDate = dates.get(finalI).toString();
                    startNewActivity(newDate);
                }
            });
        }
    }

    private void startNewActivity(String newDate) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(CalendarActivity.DATE_ID_KEY, newDate);
        startActivity(intent);
    }

    /**
     * Set visibility of the circle around chosen date
     */
    private void setImageViewsVisibility() {
        ArrayList<ImageView> imageViews = new ArrayList<>(Arrays.asList(circleMonday, circleTuesday, circleWednesday, circleThursday, circleFriday, circleSaturday, circleSunday));
        for (int i = 0; i <= 6; i++) {
            if (dayOfWeek.equals(i + 1)) {
                imageViews.get(i).setVisibility(View.VISIBLE);
            } else {
                imageViews.get(i).setVisibility(View.GONE);
            }
        }
    }

    /**
     * Set the chosen date and and calculate what day of week it is
     * Set year and month at the top of activity
     */
    private void setChosenDate() {
        String chosenDateStr = getArguments().getString(CalendarActivity.DATE_ID_KEY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        chosenDate = LocalDate.parse(chosenDateStr, formatter);
        dayOfWeek = chosenDate.getDayOfWeek().getValue();

        DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        txtYearMonth.setText(chosenDate.format(yearMonthFormatter));
    }

    private void initViews(View view) {
        txtMonday = view.findViewById(R.id.txtTopFragmentMondayNumber);
        txtTuesday = view.findViewById(R.id.txtTopFragmentTuesdayNumber);
        txtWednesday = view.findViewById(R.id.txtTopFragmentWednesdayNumber);
        txtThursday = view.findViewById(R.id.txtTopFragmentThursdayNumber);
        txtFriday = view.findViewById(R.id.txtTopFragmentFridayNumber);
        txtSaturday = view.findViewById(R.id.txtTopFragmentSaturdayNumber);
        txtSunday = view.findViewById(R.id.txtTopFragmentSundayNumber);
        circleMonday = view.findViewById(R.id.topFragmentCircleMonday);
        circleTuesday = view.findViewById(R.id.topFragmentCircleTuesday);
        circleWednesday = view.findViewById(R.id.topFragmentCircleWednesday);
        circleThursday = view.findViewById(R.id.topFragmentCircleThursday);
        circleFriday = view.findViewById(R.id.topFragmentCircleFriday);
        circleSaturday = view.findViewById(R.id.topFragmentCircleSaturday);
        circleSunday = view.findViewById(R.id.topFragmentCircleSunday);
        btnPreviousWeek = view.findViewById(R.id.btnTopFragmentPrevious);
        btnNextWeek = view.findViewById(R.id.btnTopFragmentNext);
        txtYearMonth = view.findViewById(R.id.topFragmentYearMonth);
    }
}

package com.example.psami_projekt.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.psami_projekt.R;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TopFragment extends Fragment {

    private TextView txtMonday, txtTuesday, txtWednesday, txtThursday, txtFriday, txtSaturday, txtSunday;
    private ImageView circleMonday, circleTuesday, circleWednesday, circleThursday, circleFriday, circleSaturday, circleSunday;
    private LocalDate chosenDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        initViews(view);

        String chosenDateStr = getArguments().getString(CalendarActivity.DATE_ID_KEY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        chosenDate = LocalDate.parse(chosenDateStr, formatter);
        Integer dayOfWeekOfChosenDate = chosenDate.getDayOfWeek().getValue();
        ArrayList<ImageView> imageViews = new ArrayList<>(Arrays.asList(circleMonday, circleTuesday, circleWednesday, circleThursday, circleFriday, circleSaturday, circleSunday));
        for (int i = 0; i <= 6; i++) {
            if (dayOfWeekOfChosenDate.equals(i + 1)) {
                imageViews.get(i).setVisibility(View.VISIBLE);
            } else {
                imageViews.get(i).setVisibility(View.GONE);
            }
        }

        Integer dayOfWeek = chosenDate.getDayOfWeek().getValue();
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            dates.add(chosenDate.plusDays(i - dayOfWeek));
        }

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.addAll(Arrays.asList(txtMonday, txtTuesday, txtWednesday, txtThursday, txtFriday, txtSaturday, txtSunday));


        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setText(dates.get(i).format(DateTimeFormatter.ofPattern("d")));
            int finalI = i;
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageViews.get(dayOfWeekOfChosenDate - 1).setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra(CalendarActivity.DATE_ID_KEY, dates.get(finalI).toString());
                    startActivity(intent);
                }
            });
        }

        return view;
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
    }
}

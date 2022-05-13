package com.example.psami_projekt.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.R;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private ArrayList<String> daysOfMonth;
    private CalendarClickListener calendarClickListener;
    private Context context;

//    public CalendarAdapter(ArrayList<String> daysOfMonth, CalendarClickListener calendarClickListener) {
//        this.daysOfMonth = daysOfMonth;
//        this.calendarClickListener = calendarClickListener;
//    }


    public CalendarAdapter(ArrayList<String> daysOfMonth, Context context) {
        this.daysOfMonth = daysOfMonth;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_day, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.1666666666);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCalendarDay.setText(daysOfMonth.get(position));
        //holder.txtCalendarDay.setText("1");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

//    public void setDaysOfMonth(ArrayList<String> daysOfMonth) {
//        this.daysOfMonth=daysOfMonth;
//        notifyDataSetChanged();
//    }

    public interface CalendarClickListener {
        void onItemClick(int position, String txtDay);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCalendarDay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCalendarDay = itemView.findViewById(R.id.calendarDay);
        }
    }

}

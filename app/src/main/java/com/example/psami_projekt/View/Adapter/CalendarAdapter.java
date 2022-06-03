package com.example.psami_projekt.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psami_projekt.R;
import com.example.psami_projekt.View.CalendarActivity;
import com.example.psami_projekt.View.MainActivity;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private ArrayList<LocalDate> days = new ArrayList<>();
    private final Context context;

    public CalendarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_day, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.16666666); // 1/6 of height
        layoutParams.width = (int) (parent.getWidth() * 0.14285714285); // 1/7 of width
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtDay.setText(String.valueOf(days.get(position).getDayOfMonth()));
        if (!days.get(20).getMonth().equals(days.get(position).getMonth())) {
            holder.txtDay.setTextColor(ContextCompat.getColor(context, R.color.calendar_gray));
        } else {
            holder.txtDay.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        holder.txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(CalendarActivity.DATE_ID_KEY, days.get(holder.getAbsoluteAdapterPosition()).toString());
                context.startActivity(intent);
                ((Activity) context).finishAffinity();
            }
        });
    }

    public void setDays(ArrayList<LocalDate> days) {
        this.days = days;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDay = itemView.findViewById(R.id.calendarCell);
        }
    }
}

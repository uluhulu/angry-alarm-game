package com.ulianasisoeva.angryalarm.screen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.ulianasisoeva.angryalarm.R;
import com.ulianasisoeva.angryalarm.alarmservices.AlarmService;
import com.ulianasisoeva.angryalarm.model.AlarmClockModel;
import com.ulianasisoeva.angryalarm.model.Repository;
import com.ulianasisoeva.angryalarm.model.domain.Alarm;
import com.ulianasisoeva.angryalarm.model.domain.Day;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Alarm> alarmList;
    Repository repository;

    public RecyclerViewAdapter(Context context, List<Alarm> alarmList, Repository repository) {
        this.context = context;
        this.alarmList = alarmList;
        this.repository = repository;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_alarm, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        final Alarm alarm = alarmList.get(position);
        myViewHolder.time.setText(alarm.time);

        for (Day day : alarm.dayList) {
            switch (day.id) {
                case 1:
                    myViewHolder.mn.setTextColor(Color.parseColor("#FF8C00"));
                    break;

                case 2:
                    myViewHolder.tu.setTextColor(Color.parseColor("#FF8C00"));
                    break;

                case 3:
                    myViewHolder.we.setTextColor(Color.parseColor("#FF8C00"));
                    break;
                case 4:
                    myViewHolder.th.setTextColor(Color.parseColor("#FF8C00"));
                    break;

                case 5:
                    myViewHolder.fr.setTextColor(Color.parseColor("#FF8C00"));
                    break;

                case 6:
                    myViewHolder.st.setTextColor(Color.parseColor("#FF8C00"));
                    break;

                case 7:
                    myViewHolder.sn.setTextColor(Color.parseColor("#FF8C00"));
                    break;
            }
        }

        myViewHolder.alarmLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                repository.deleteAlarm(alarm.id);
                alarmList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, alarmList.size());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView mn;
        private TextView tu;
        private TextView we;
        private TextView th;
        private TextView fr;
        private TextView st;
        private TextView sn;
        private Switch aSwitch;
        private View alarmLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.alarm_item_time);
            mn = itemView.findViewById(R.id.alarm_item_monday);
            tu = itemView.findViewById(R.id.alarm_item_tuesday);
            we = itemView.findViewById(R.id.alarm_item_wednesday);
            th = itemView.findViewById(R.id.alarm_item_thursday);
            fr = itemView.findViewById(R.id.alarm_item_friday);
            st = itemView.findViewById(R.id.alarm_item_saturday);
            sn = itemView.findViewById(R.id.alarm_item_sunday);
            aSwitch = itemView.findViewById(R.id.alarm_item_switch);
            alarmLayout = itemView.findViewById(R.id.alarm_layout);
        }
    }

}

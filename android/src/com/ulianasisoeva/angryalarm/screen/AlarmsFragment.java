package com.ulianasisoeva.angryalarm.screen;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import com.ulianasisoeva.angryalarm.R;
import com.ulianasisoeva.angryalarm.alarmservices.AlarmService;
import com.ulianasisoeva.angryalarm.alarmservices.Restarter;
import com.ulianasisoeva.angryalarm.model.AlarmClockModel;
import com.ulianasisoeva.angryalarm.model.AlarmComparator;
import com.ulianasisoeva.angryalarm.model.Repository;
import com.ulianasisoeva.angryalarm.model.domain.Alarm;
import com.ulianasisoeva.angryalarm.model.domain.Day;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class AlarmsFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private List<AlarmClockModel> alarmList;
    FloatingActionButton floatingActionButton;
    Repository repository;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alarms, container, false);
        recyclerView = view.findViewById(R.id.alarms_recyclerView);

        repository = new Repository(getContext());
        ArrayList<Alarm> alarms = repository.getAlarms();
        alarms.sort(new AlarmComparator());
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), alarms, repository);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AlarmSettingsActivity.class);
                startActivity(i);
            }
        });
        Intent intent = new Intent(getContext(), AlarmService.class);
        test(alarms);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void test(ArrayList<Alarm> alarms) {

        for (int i = 0; i < alarms.size(); i++) {
            Alarm alarm = alarms.get(i);
            Calendar calendar = Calendar.getInstance();
            int hours = Integer.parseInt(alarm.time.split(":")[0]);
            int minutes = Integer.parseInt(alarm.time.split(":")[1]);

            System.out.println("time - " + hours + " : " + minutes);
            System.out.println("calendar - " + calendar.toString());
            calendar.set(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    hours,
                    minutes,
                    0
            );


            if (alarm.isEnable) {
                AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                AlarmManager.OnAlarmListener alarmListener = new AlarmManager.OnAlarmListener() {
                    @Override
                    public void onAlarm() {
                        startActivity(new Intent(AlarmsFragment.this.getContext(), AndroidLauncher.class));
                    }
                };
                Handler targetHandler = new Handler();

                Calendar currentCalendar = Calendar.getInstance();
                if (currentCalendar.getTimeInMillis() <= calendar.getTimeInMillis()) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), "Alarm", alarmListener, targetHandler);
                }
            }
        }
    }
}

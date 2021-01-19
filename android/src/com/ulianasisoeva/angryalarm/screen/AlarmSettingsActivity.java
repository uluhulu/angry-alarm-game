package com.ulianasisoeva.angryalarm.screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ulianasisoeva.angryalarm.R;
import com.ulianasisoeva.angryalarm.alarmservices.AlarmService;
import com.ulianasisoeva.angryalarm.model.Repository;
import com.ulianasisoeva.angryalarm.model.domain.Alarm;
import com.ulianasisoeva.angryalarm.model.domain.Day;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class AlarmSettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TimePicker timePicker;
    private PlanTextView textViewMonday;
    private PlanTextView textViewTuesday;
    private PlanTextView textViewWednesday;
    private PlanTextView textViewThursday;
    private PlanTextView textViewFriday;
    private PlanTextView textViewSaturday;
    private PlanTextView textViewSunday;
    private ImageButton saveAlarm;
    private ImageButton back;
    private AlarmService alarmService;

    Repository repository;
    private ArrayList<Day> selectedDays = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_settings);

        repository = new Repository(this);
        timePicker = findViewById(R.id.timePicker);
        alarmService = new AlarmService();

        toolbar = findViewById(R.id.toolbar_settings);


        saveAlarm = findViewById(R.id.set_alarm_button);

        saveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }
                System.out.println("ass " + timePicker.getCurrentHour());
                if(calendar.getTime().getMinutes() < 10){
                    String time = calendar.getTime().getHours() + ":" +"0"+ calendar.getTime().getMinutes();
                    if (selectedDays.size()!= 0){
                        repository.insertAlarm(new Alarm(new Random().nextInt(100), time, true, 0, selectedDays));
                        startAlarmService(calendar);
                        startActivity(new Intent(AlarmSettingsActivity.this, TabLayoutActivity.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Choose days!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    String time = calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes();
                    if (selectedDays.size()!= 0){
                        repository.insertAlarm(new Alarm(new Random().nextInt(100), time, true, 0, selectedDays));
//                        startAlarmService(calendar);
                        startActivity(new Intent(AlarmSettingsActivity.this, TabLayoutActivity.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Choose days!", Toast.LENGTH_SHORT).show();
                    }

                }

//                startAlarmService(calendar);
//                startActivity(new Intent(AlarmSettingsActivity.this, TabLayoutActivity.class));
            }
        });

        back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        textViewMonday = findViewById(R.id.textView1);
        textViewMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day monday = new Day(1, "Пн");
                PlanTextView view = (PlanTextView) v;
                if (view.is_stateChanged()) {
                    view.setBackgroundResource(R.drawable.circle_off);
                    view.set_selected(false);
                    removeDay(monday);
                } else {
                    view.setBackgroundResource(R.drawable.circle_on);
                    view.set_selected(true);
                    selectDay(monday);
                }
                view.set_stateChanged(!view.is_stateChanged());
            }
        });
        textViewTuesday = findViewById(R.id.textView2);
        textViewTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day tuesday = new Day(2, "Вт");
                PlanTextView view = (PlanTextView) v;
                if (view.is_stateChanged()) {
                    view.setBackgroundResource(R.drawable.circle_off);
                    view.set_selected(false);
                    removeDay(tuesday);
                } else {
                    view.setBackgroundResource(R.drawable.circle_on);
                    view.set_selected(true);
                    selectDay(tuesday);
                }
                view.set_stateChanged(!view.is_stateChanged());
            }
        });
        textViewWednesday = findViewById(R.id.textView3);
        textViewWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day wednesday = new Day(3, "Ср");
                PlanTextView view = (PlanTextView) v;
                if (view.is_stateChanged()) {
                    view.setBackgroundResource(R.drawable.circle_off);
                    view.set_selected(false);
                    removeDay(wednesday);
                } else {
                    view.setBackgroundResource(R.drawable.circle_on);
                    view.set_selected(true);
                    selectDay(wednesday);
                }
                view.set_stateChanged(!view.is_stateChanged());
            }
        });
        textViewThursday = findViewById(R.id.textView4);
        textViewThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day thursday = new Day(4, "Чт");
                PlanTextView view = (PlanTextView) v;
                if (view.is_stateChanged()) {
                    view.setBackgroundResource(R.drawable.circle_off);
                    view.set_selected(false);
                    removeDay(thursday);
                } else {
                    view.setBackgroundResource(R.drawable.circle_on);
                    view.set_selected(true);
                    selectDay(thursday);
                }
                view.set_stateChanged(!view.is_stateChanged());
            }
        });
        textViewFriday = findViewById(R.id.textView5);
        textViewFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day friday = new Day(5, "Пт");
                PlanTextView view = (PlanTextView) v;
                if (view.is_stateChanged()) {
                    view.setBackgroundResource(R.drawable.circle_off);
                    view.set_selected(false);
                    removeDay(friday);
                } else {
                    view.setBackgroundResource(R.drawable.circle_on);
                    view.set_selected(true);
                    selectDay(friday);
                }
                view.set_stateChanged(!view.is_stateChanged());
            }
        });
        textViewSaturday = findViewById(R.id.textView6);
        textViewSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day saturday = new Day(6, "Сб");
                PlanTextView view = (PlanTextView) v;
                if (view.is_stateChanged()) {
                    view.setBackgroundResource(R.drawable.circle_off);
                    view.set_selected(false);
                    removeDay(saturday);
                } else {
                    view.setBackgroundResource(R.drawable.circle_on);
                    view.set_selected(true);
                    selectDay(saturday);
                }
                view.set_stateChanged(!view.is_stateChanged());
            }
        });
        textViewSunday = findViewById(R.id.textView7);
        textViewSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day sunday = new Day(7, "Вс");
                PlanTextView view = (PlanTextView) v;
                if (view.is_stateChanged()) {
                    view.setBackgroundResource(R.drawable.circle_off);
                    view.set_selected(false);
                    removeDay(sunday);
                } else {
                    view.setBackgroundResource(R.drawable.circle_on);
                    view.set_selected(true);
                    selectDay(sunday);
                }
                view.set_stateChanged(!view.is_stateChanged());
            }
        });


    }

    private void startAlarmService(Calendar calendar) {
        Intent intent = new Intent(this, alarmService.getClass());
        intent.putExtra("calendar", calendar);
        startService(intent);
    }

    private void selectDay(Day day) {
        selectedDays.add(day);
    }

    private void removeDay(Day day) {
        selectedDays.remove(day);
    }


}


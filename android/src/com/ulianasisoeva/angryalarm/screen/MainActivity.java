package com.ulianasisoeva.angryalarm.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.ulianasisoeva.angryalarm.R;
import com.ulianasisoeva.angryalarm.alarmservices.AlarmService;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private AlarmService alarmService;
    TimePicker timePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmService = new AlarmService();

        timePicker = findViewById(R.id.timePicker);

        findViewById(R.id.buttonAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We need a calendar object to get the specified time in millis
                //as the alarm manager method takes time in millis to setup the alarm
                Calendar calendar = Calendar.getInstance();
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }
//                startAlarmService(calendar);
            }
        });
    }

    private void startAlarmService(Calendar calendar) {
        Intent intent = new Intent(this, alarmService.getClass());
        intent.putExtra("calendar", calendar);
        startService(intent);
    }

}


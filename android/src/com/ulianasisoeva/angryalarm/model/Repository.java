package com.ulianasisoeva.angryalarm.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.ulianasisoeva.angryalarm.model.db.DatabaseHelper;
import com.ulianasisoeva.angryalarm.model.domain.Alarm;
import com.ulianasisoeva.angryalarm.model.domain.Day;

import java.util.ArrayList;

import static com.ulianasisoeva.angryalarm.model.db.Script.DELETE_ALARAM;
import static com.ulianasisoeva.angryalarm.model.db.Script.DELETE_ALARM_REL;
import static com.ulianasisoeva.angryalarm.model.db.Script.GET_ALARMS;
import static com.ulianasisoeva.angryalarm.model.db.Script.GET_DAYS_FROM_ALARM;
import static com.ulianasisoeva.angryalarm.model.db.Script.UPDATE_ALARAM;

public class Repository {

    private SQLiteDatabase db;

    public Repository(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context, "database", null, 11);
        db = databaseHelper.getReadableDatabase();
    }

    public ArrayList<Alarm> getAlarms() {
        ArrayList<Alarm> alarmList = new ArrayList<>();
        Cursor res = db.rawQuery(GET_ALARMS, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex("id"));
            String time = res.getString(res.getColumnIndex("time"));
            int score = res.getInt(res.getColumnIndex("score"));
            boolean isEnable = res.getInt(res.getColumnIndex("isEnable")) == 1;

            Alarm alarm = new Alarm(id, time, isEnable, score, getDaysFromAlarm(id));

            alarmList.add(alarm);

            res.moveToNext();
        }

        System.out.println("все " + alarmList);
        return alarmList;
    }


    private ArrayList<Day> getDaysFromAlarm(int alarmId) {
        ArrayList<Day> dayList = new ArrayList<>();
        Cursor res = db.rawQuery(GET_DAYS_FROM_ALARM(alarmId), null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex("id"));
            String name = res.getString(res.getColumnIndex("name"));

            Day day = new Day(id, name);

            dayList.add(day);

            res.moveToNext();
        }
        return dayList;
    }

    public void insertAlarm(Alarm alarm) {
        db.beginTransaction();
        ContentValues alarmValues = new ContentValues();
        alarmValues.put("id", alarm.id);
        alarmValues.put("time", alarm.time);
        alarmValues.put("isEnable", alarm.isEnable ? 1 : 0);
        alarmValues.put("score", alarm.score);
        db.insert("alarm", null, alarmValues);
        db.setTransactionSuccessful();
        db.endTransaction();
        for (Day d : alarm.dayList) {
            ContentValues relValues = new ContentValues();
            relValues.put("alarm_id", alarm.id);
            relValues.put("day_id", d.id);
            db.beginTransaction();
            db.insert("alarm_day_rel", null, relValues);
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    public void deleteAlarm(int alarmId) {
        db.execSQL(DELETE_ALARAM(alarmId));
        db.execSQL(DELETE_ALARM_REL(alarmId));
    }

    public void updateAlarm(Alarm alarm) {
        db.execSQL(UPDATE_ALARAM(alarm));
    }
}

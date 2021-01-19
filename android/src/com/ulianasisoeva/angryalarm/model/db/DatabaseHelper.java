package com.ulianasisoeva.angryalarm.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.ulianasisoeva.angryalarm.model.domain.Day;

import static com.ulianasisoeva.angryalarm.model.db.Script.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initBase(db);
    }

    private void initBase(SQLiteDatabase db) {
        db.execSQL(CREATE_ALARM_TABLE);
        db.execSQL(CREATE_DAYS_TABLE);
        db.execSQL(CREATE_ALARM_DAY_REL_TABLE);
        db.execSQL(INIT_DAYS_TABLE(new Day(1, "Пн")));
        db.execSQL(INIT_DAYS_TABLE(new Day(2, "Вт")));
        db.execSQL(INIT_DAYS_TABLE(new Day(3, "Ср")));
        db.execSQL(INIT_DAYS_TABLE(new Day(4, "Чт")));
        db.execSQL(INIT_DAYS_TABLE(new Day(5, "Пт")));
        db.execSQL(INIT_DAYS_TABLE(new Day(6, "Сб")));
        db.execSQL(INIT_DAYS_TABLE(new Day(7, "Вс")));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarm");
        db.execSQL("DROP TABLE IF EXISTS day");
        db.execSQL("DROP TABLE IF EXISTS alarm_day_rel");
        onCreate(db);
    }
}

package com.ulianasisoeva.angryalarm.model.db;

import com.ulianasisoeva.angryalarm.model.domain.Alarm;
import com.ulianasisoeva.angryalarm.model.domain.Day;

public class Script {

    public static final String CREATE_ALARM_TABLE = "create table alarm" +
            "(id integer primary key," +
            "time text not null," +
            "score integer not null," +
            "isEnable integer not null);";

    public static final String CREATE_DAYS_TABLE = "create table day" +
            "(id integer primary key," +
            "name text not null);";

    public static String INIT_DAYS_TABLE(Day day) {
        return "insert into day(name) values('" + day.name + "');";
    }

    public static final String CREATE_ALARM_DAY_REL_TABLE = "create table alarm_day_rel" +
            "(alarm_id integer not null," +
            "day_id integer not null);";

    public static String DELETE_ALARAM(int alarmId) {
        return "DELETE FROM alarm WHERE id = " + alarmId + ";";
    }

    public static String DELETE_ALARM_REL(int alarmId) {
        return "DELETE FROM alarm_day_rel where alarm_id = " + alarmId + ";";
    }

    public static String UPDATE_ALARAM(Alarm alarm) {
        int isEnableInt = alarm.isEnable ? 1 : 0;
        return "UPDATE alarm" +
                " SET " +
                " time = '" + alarm.time + "'," +
                " score = " + alarm.score + "," +
                " isEnable = " + isEnableInt +
                " WHERE id = " + alarm.id +
                ";";
    }

    public static String INSERT_ALARM(Alarm alarm) {
        int isEnableInt = alarm.isEnable ? 1 : 0;
        return "insert into alarm(time, score, isEnable)values('" + alarm.time + "'," + alarm.score + "," + isEnableInt + ");";
    }

    public static String GET_ALARMS = "select * from alarm;";

    public static String GET_ALARM_BY_NAME(String name) {
        return "select * from alarm where name = " + name;
    }

    public static String GET_DAYS_FROM_ALARM(int alarmId) {
        return "select d.id, d.name from alarm a, day d, alarm_day_rel rel" +
                " where a.id = rel.alarm_id" +
                " and d.id = rel.day_id" +
                " and a.id = " + alarmId + ";";
    }

    public static String INSERT_ALARM_DAY_REL(Alarm alarm, int dayId) {
        return "insert into alarm_day_rel(alarm_id, day_id)" +
                "values(" + alarm.id + ", " + dayId + ");";
    }
}

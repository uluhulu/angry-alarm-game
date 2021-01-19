package com.ulianasisoeva.angryalarm.model;

public class AlarmClockModel {
    private String time;
    private int dayOfWeek;
    private boolean isEnabled;

    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SUNDAY = 7;

    public AlarmClockModel(String time, int dayOfWeek, boolean isEnabled) {
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.isEnabled = isEnabled;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}

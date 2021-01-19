package com.ulianasisoeva.angryalarm.model.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Alarm implements Serializable {
    public int id;
    public String time;
    public boolean isEnable;
    public int score;

    public ArrayList<Day> dayList;

    public Alarm(int id, String time, boolean isEnable, int score, ArrayList<Day> dayList) {
        this.id = id;
        this.time = time;
        this.isEnable = isEnable;
        this.score = score;
        this.dayList = dayList;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", isEnable=" + isEnable +
                ", score=" + score +
                ", days=" + dayList.toString() +
                '}';
    }
}

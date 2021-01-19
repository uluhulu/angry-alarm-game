package com.ulianasisoeva.angryalarm.model;

import com.ulianasisoeva.angryalarm.model.domain.Alarm;

import java.util.Comparator;

public class AlarmComparator implements Comparator<Alarm> {
    @Override
    public int compare(Alarm a1, Alarm a2) {
        String[] alarm1Time = a1.time.split(":");
        int alarm1Hour = Integer.parseInt(alarm1Time[0]);
        int alarm1Minute = Integer.parseInt(alarm1Time[1]);

        String[] alarm2Time = a2.time.split(":");
        int alarm2Hour = Integer.parseInt(alarm2Time[0]);
        int alarm2Minute = Integer.parseInt(alarm2Time[1]);

        if (alarm1Hour > alarm2Hour) {
            return 1;
        } else if (alarm1Hour < alarm2Hour) {
            return -1;
        } else {
            if (alarm1Minute > alarm2Minute) {
                return 1;
            } else if (alarm1Minute > alarm2Minute) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

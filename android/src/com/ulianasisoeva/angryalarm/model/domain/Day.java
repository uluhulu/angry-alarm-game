package com.ulianasisoeva.angryalarm.model.domain;

public class Day {
    public int id;
    public String name;

    public Day(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

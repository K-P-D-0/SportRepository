package org.example;

public class Data {
    private final Integer day;
    private final Integer month;
    private final Integer year;

    public Data() {
        day = 0;
        month = 0;
        year = 0;
    }

    public Data(Integer day, Integer month, Integer year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        return day.toString() + "." + month.toString() + "." + year.toString();
    }
}

package com.example.http.client;

public class DummyDto {

    public String time;
    public long milliseconds_since_epoch;
    public String date;

    public DummyDto() {}

    public DummyDto(String time, long milliseconds_since_epoch, String date) {
        this.time = time;
        this.milliseconds_since_epoch = milliseconds_since_epoch;
        this.date = date;
    }
}
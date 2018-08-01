package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeHandler implements Handler {
    private TimeHandler() {

    }

    public static TimeHandler create() {
        return new TimeHandler();
    }

    @Override
    public String handle(String inputRow) {
        return LocalDateTime.parse(inputRow)
                .toLocalTime()
                .format(DateTimeFormatter.ofPattern("H:mm"));
    }
}

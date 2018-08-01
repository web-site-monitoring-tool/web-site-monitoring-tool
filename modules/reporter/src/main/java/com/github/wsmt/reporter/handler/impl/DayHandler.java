package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;

import java.time.LocalDateTime;

public class DayHandler implements Handler {

    private DayHandler() {

    }

    public static DayHandler create() {
        return new DayHandler();
    }

    @Override
    public String handle(String inputRow) {
        return LocalDateTime.parse(inputRow)
                .getDayOfWeek()
                .toString();
    }
}

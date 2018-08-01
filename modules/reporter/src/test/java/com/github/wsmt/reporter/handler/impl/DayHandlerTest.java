package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class DayHandlerTest {

    @Test
    public void create() {
        Handler dayHandler = DayHandler.create();
        assertNotNull(dayHandler);
    }

    @Test
    public void handle() {
        Handler dayHandler = DayHandler.create();
        LocalDateTime dateTime = LocalDateTime.now();
        String day = dayHandler.handle(dateTime.toString());

        assertEquals(dateTime.getDayOfWeek().toString(), day);
    }
}
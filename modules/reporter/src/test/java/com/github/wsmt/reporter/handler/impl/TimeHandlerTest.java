package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class TimeHandlerTest {

    @Test
    public void create() {
        Handler timeHandler = TimeHandler.create();
        assertNotNull(timeHandler);
    }

    @Test
    public void handle() {
        Handler timeHandler = TimeHandler.create();
        LocalDateTime dateTime = LocalDateTime.now();
        String time = timeHandler.handle(dateTime.toString());

        assertEquals(dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("H:mm")), time);

    }
}
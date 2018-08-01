package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserAgentHandlerTest {

    @Test
    public void create() {
        Handler userAgentHandler = UserAgentHandler.create();
        assertNotNull(userAgentHandler);
    }

    @Test
    public void handle() {
        Handler userAgentHandler = UserAgentHandler.create();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";

        assertEquals("Chrome", userAgentHandler.handle(userAgent));
    }
}
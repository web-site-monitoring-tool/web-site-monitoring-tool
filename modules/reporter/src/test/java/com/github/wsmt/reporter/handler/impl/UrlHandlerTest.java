package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlHandlerTest {

    @Test
    public void create() {
        Handler urlHandler = UrlHandler.create();
        assertNotNull(urlHandler);
    }

    @Test
    public void handle() {
        Handler urlHandler = UrlHandler.create();
        String url = "https://www.dev.com/2800";
        assertEquals(url, urlHandler.handle(url));
    }
}
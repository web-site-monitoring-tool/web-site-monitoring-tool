package com.github.wsmt.reporter.handler;

import com.github.wsmt.reporter.handler.impl.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RowHandlerFactoryTest {
    @Test
    public void getCountryHandler() {
        Handler countryHandler = RowHandlerFactory.getHandler("country");
        assertTrue(countryHandler instanceof CountryHandler);
    }

    @Test
    public void getUrlHandler() {
        Handler countryHandler = RowHandlerFactory.getHandler("url");
        assertTrue(countryHandler instanceof UrlHandler);
    }

    @Test
    public void getBrowserHandler() {
        Handler countryHandler = RowHandlerFactory.getHandler("browser");
        assertTrue(countryHandler instanceof UserAgentHandler);
    }

    @Test
    public void getDayHandler() {
        Handler countryHandler = RowHandlerFactory.getHandler("day");
        assertTrue(countryHandler instanceof DayHandler);
    }

    @Test
    public void getTimeHandler() {
        Handler countryHandler = RowHandlerFactory.getHandler("time");
        assertTrue(countryHandler instanceof TimeHandler);
    }
}
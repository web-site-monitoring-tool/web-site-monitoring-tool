package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.db.impl.GeoIpCountry;
import com.github.wsmt.reporter.handler.Handler;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountryHandlerTest {
    @Test
    public void testCreation() {
        Handler countryHandler = CountryHandler.byGeoDb(GeoIpCountry.byDbName("GeoLite2-City.mmdb"));
        assertNotNull(countryHandler);
    }

    @Test
    public void testHandle() {
        Handler countryHandler = CountryHandler.byGeoDb(GeoIpCountry.byDbName("GeoLite2-City.mmdb"));
        String country = countryHandler.handle("94.251.95.50");
        assertEquals("Russia", country);
    }

    @Test
    public void testHandleWithNotCorrectRow() {
        Handler countryHandler = CountryHandler.byGeoDb(GeoIpCountry.byDbName("GeoLite2-City.mmdb"));
        String country = countryHandler.handle("94.251..50");
        assertEquals("Unknown", country);
    }
}
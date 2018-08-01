package com.github.wsmt.reporter.db.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeoIpCountryTest {
    @Test
    public void byDbName() {
        GeoIpCountry geoIpCountry = GeoIpCountry.byDbName("GeoLite2-City.mmdb");
        assertNotNull(geoIpCountry);
    }

    @Test
    public void getLocation() {
        GeoIpCountry geoIpCountry = GeoIpCountry.byDbName("GeoLite2-City.mmdb");
        String country = geoIpCountry.getLocation("94.251.95.50");
        assertEquals("Russia", country);
    }

    @Test
    public void getLocationWithNotCorrectIp() {
        GeoIpCountry geoIpCountry = GeoIpCountry.byDbName("GeoLite2-City.mmdb");
        String country = geoIpCountry.getLocation("94.251.95eee");
        assertEquals("Unknown", country);
    }
}
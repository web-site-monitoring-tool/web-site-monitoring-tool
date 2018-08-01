package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.db.GeoIp;
import com.github.wsmt.reporter.handler.Handler;


public class CountryHandler implements Handler {
    private static GeoIp geoIpDb;

    private CountryHandler(GeoIp geoIpDb) {
        this.geoIpDb = geoIpDb;
    }

    public static CountryHandler byGeoDb(GeoIp geoIp) {
        return new CountryHandler(geoIp);
    }

    @Override
    public String handle(String inputRow) {
        return geoIpDb.getLocation(inputRow);
    }
}

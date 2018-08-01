package com.github.wsmt.reporter.db.impl;

import com.github.wsmt.reporter.db.GeoIp;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

public class GeoIpCountry implements GeoIp {

    private DatabaseReader databaseReader;

    public GeoIpCountry(String dbName) {
        initDb(dbName);
    }

    public static GeoIpCountry byDbName(String dbName) {
        return new GeoIpCountry(dbName);
    }

    private void initDb(String dbName) {

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(dbName)) {

            File file = File.createTempFile("file", "temp");
            FileUtils.copyInputStreamToFile(inputStream, file);
            databaseReader = new DatabaseReader.Builder(file).build();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getLocation(String ipAddress) {

        if (ipAddress.contains(":")) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(":")); //erase port
        }
        if (ipAddress.equals("localhost")) {
            //return "Russia";
            return Locale.getDefault().getCountry();
        }
        InetAddress inetAddress = null;

        try {
            inetAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            return "Unknown";
        }

        CityResponse response = null;

        try {
            response = databaseReader.city(inetAddress);
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
            return "Unknown";
        }

        String country = response.getCountry().getName();
        return country != null ? country : "Unknown";

//        System.out.println(country.getIsoCode());            // 'US'
//        System.out.println(country.getName());               // 'United States'
//
//        Subdivision subdivision = response.getMostSpecificSubdivision();
//        System.out.println(subdivision.getName());    // 'Minnesota'
//        System.out.println(subdivision.getIsoCode()); // 'MN'
//
//        City city = response.getCity();
//        System.out.println(city.getName()); // 'Minneapolis'
//
//        Postal postal = response.getPostal();
//        System.out.println(postal.getCode()); // '55455'
//
//        Location location = response.getLocation();
//        System.out.println(location.getLatitude());  // 44.9733
//        System.out.println(location.getLongitude()); // -93.2323
    }
}

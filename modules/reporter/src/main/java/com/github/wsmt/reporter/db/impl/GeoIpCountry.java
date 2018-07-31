package com.github.wsmt.reporter.db;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Locale;

public class GeoIpCountry {

    private DatabaseReader databaseReader;

    private GeoIpCountry() {
        initDb();
    }

    public static GeoIpCountry create() {
        return new GeoIpCountry();
    }

    private void initDb() {
        String dbName = "GeoLite2-City.mmdb"
        try (InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("GeoLite2-City.mmdb")) {

            File file = File.createTempFile("file", "temp");
            FileUtils.copyInputStreamToFile(inputStream, file);
            databaseReader = new DatabaseReader.Builder(file).build();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getCountry(String ipAddress) throws IOException, GeoIp2Exception {

        if (ipAddress.contains(":")) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(":")); //erase port
        }
        if (ipAddress.equals("localhost")) {
            return Locale.getDefault().getCountry();
        }

        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse response = databaseReader.city(inetAddress);
        return response.getCountry().getName();
//            System.out.println(country.getIsoCode());            // 'US'
//            System.out.println(country.getName());               // 'United States'
//
//            Subdivision subdivision = response.getMostSpecificSubdivision();
//            System.out.println(subdivision.getName());    // 'Minnesota'
//            System.out.println(subdivision.getIsoCode()); // 'MN'
//
//            City city = response.getCity();
//            System.out.println(city.getName()); // 'Minneapolis'
//
//            Postal postal = response.getPostal();
//            System.out.println(postal.getCode()); // '55455'
//
//            Location location = response.getLocation();
//            System.out.println(location.getLatitude());  // 44.9733
//            System.out.println(location.getLongitude()); // -93.2323
    }
}

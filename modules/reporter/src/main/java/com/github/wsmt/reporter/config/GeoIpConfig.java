package com.github.wsmt.reporter.config;

import com.github.wsmt.reporter.db.GeoIp;
import com.github.wsmt.reporter.db.impl.GeoIpCountry;
import com.github.wsmt.reporter.util.SerializableSupplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GeoIpConfig {
//    @Bean
//    public SerializableSupplier<GeoIp> geoIpSupplier(@Value("${geoipname}") String geoIpName) {
//        return () -> GeoIpCountry.byDbName(geoIpName);
//    }
}

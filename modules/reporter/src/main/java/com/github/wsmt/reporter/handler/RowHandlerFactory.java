package com.github.wsmt.reporter.handler;

import com.github.wsmt.reporter.db.impl.GeoIpCountry;
import com.github.wsmt.reporter.handler.impl.CountryHandler;
import com.github.wsmt.reporter.handler.impl.UrlHandler;
import com.github.wsmt.reporter.handler.impl.UserAgentHandler;

import java.util.HashMap;
import java.util.Map;

public class RowHandlerFacktory {

    private static Handler countryHandler = CountryHandler.byGeoDb(GeoIpCountry.byDbName("GeoLite2-City.mmdb"));
    private static Handler urlHandler = UrlHandler.create();
    private static Handler userAgentHandler = UserAgentHandler.create();

    private static Map<String, Handler> handlerMap;

    private RowHandlerFacktory() {

        handlerMap = new HashMap<>();
        handlerMap.put("country", countryHandler);
        handlerMap.put("url", urlHandler);
        handlerMap.put("browser", userAgentHandler);
    }

    public static Handler getHandler(String column) {
        return handlerMap.get(column);
    }
}

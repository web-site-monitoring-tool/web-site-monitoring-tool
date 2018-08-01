package com.github.wsmt.reporter.handler;

import com.github.wsmt.reporter.db.impl.GeoIpCountry;
import com.github.wsmt.reporter.handler.impl.*;

import java.util.HashMap;
import java.util.Map;

public class RowHandlerFactory {

    private static Handler countryHandler = CountryHandler.byGeoDb(GeoIpCountry.byDbName("GeoLite2-City.mmdb"));
    private static Handler urlHandler = UrlHandler.create();
    private static Handler userAgentHandler = UserAgentHandler.create();
    private static Handler dayHandler = DayHandler.create();
    private static Handler timeHandler = TimeHandler.create();

    private static Map<String, Handler> handlerMap;

    static {
        handlerMap = new HashMap<>();
        handlerMap.put("country", countryHandler);
        handlerMap.put("url", urlHandler);
        handlerMap.put("browser", userAgentHandler);
        handlerMap.put("day", dayHandler);
        handlerMap.put("time", timeHandler);
    }

    public static Handler getHandler(String column) {
        return handlerMap.get(column);
    }
}

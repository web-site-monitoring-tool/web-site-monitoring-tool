package com.github.wsmt.reporter.config;

import com.github.wsmt.reporter.db.GeoIp;
import com.github.wsmt.reporter.handler.ReportHandler;
import com.github.wsmt.reporter.handler.impl.UrlHandler;
import com.github.wsmt.reporter.handler.impl.UserAgentHandler;
import com.github.wsmt.reporter.handler.impl.CountryHandler;
import com.github.wsmt.reporter.service.ReportService;
import com.github.wsmt.reporter.util.SerializableSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class HandlerConfig {

    @Bean
    public RouterFunction<ServerResponse> reportRouter(ReportHandler reportHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/report"), reportHandler);
    }

    @Bean
    public ReportHandler reportHandler(ReportService reportService) {
        return ReportHandler.byService(reportService);
    }
}

package com.github.wsmt.tracker.config;

import com.github.wsmt.tracker.handler.DBHandler;
import com.github.wsmt.tracker.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class DBConfig {
    @Bean
    public RouterFunction<ServerResponse> dBRouter(DBHandler dbHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/clear"), dbHandler);
    }
    @Bean
    public DBHandler dbHandler(UserService userService) {
        return DBHandler.create(userService);
    }
}

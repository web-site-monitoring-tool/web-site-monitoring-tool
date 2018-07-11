package com.github.wsmt.tracker.config;

import com.github.wsmt.tracker.handler.PixelHandler;
import com.github.wsmt.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PixelConfig {
    @Bean
    public RouterFunction<ServerResponse> pixelRouter(PixelHandler pixelHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/pixel"), pixelHandler);
    }

    @Bean
    public PixelHandler pixelHandler(@Value("classpath:/pixel.png") Resource pixel,
                                     @Value("${profile.id.cookie-name}") String profileIdCookieName,
                                     UserService userService) {
        return PixelHandler.newPixelHandler(pixel, profileIdCookieName, userService);
    }
}

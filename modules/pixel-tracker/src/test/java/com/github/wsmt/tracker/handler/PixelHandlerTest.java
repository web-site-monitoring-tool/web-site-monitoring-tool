package com.github.wsmt.tracker.handler;

import com.github.wsmt.tracker.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpCookie;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class PixelHandlerTest {

    @Test
    public void testCreation() {
        UserService userService = mock(UserService.class);
        Resource resource = mock(Resource.class);
        String profileIdCookieName = "123456";
        PixelHandler pixelHandler = PixelHandler.newPixelHandler(resource, profileIdCookieName, userService);

        assertNotNull(pixelHandler);
    }

    @Ignore
    @Test
    public void testGetUser() {
        UserService userService = mock(UserService.class);
        Resource resource = mock(Resource.class);
        ServerRequest serverRequest = mock(ServerRequest.class);

        String cookieName = "cookie";
        String cookieValue = "123456";

        when(serverRequest
                .cookies()
                .getFirst(cookieName)
        ).thenReturn(new HttpCookie(cookieName, cookieValue));

        PixelHandler pixelHandler = PixelHandler.newPixelHandler(resource, cookieName, userService);
        Mono<ServerResponse> responseMono = pixelHandler.handle(serverRequest);

        assertEquals(200, responseMono.block().statusCode().value());
    }

}
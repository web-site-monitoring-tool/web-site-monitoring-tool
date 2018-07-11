package com.github.wsmt.tracker.handler;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpCookie;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.ResponseCookie;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PixelHandler implements HandlerFunction<ServerResponse> {
    private static final Logger log = LoggerFactory.getLogger(PixelHandler.class);

    private final BodyInserter<Resource, ReactiveHttpOutputMessage> responseBody;
    private final String profileIdCookieName;
    private final Configuration hBaseConfiguration;
    private final TableName profileTable;

    private PixelHandler(BodyInserter<Resource, ReactiveHttpOutputMessage> responseBody,
                         String profileIdCookieName,
                         Configuration hBaseConfiguration,
                         TableName profileTable) {
        this.responseBody = responseBody;
        this.profileIdCookieName = profileIdCookieName;
        this.hBaseConfiguration = hBaseConfiguration;
        this.profileTable = profileTable;
    }

    public static PixelHandler newPixelHandler(Resource pixel,
                                               String profileIdCookieName,
                                               Configuration hBaseConfiguration,
                                               String profileTable) {
        return new PixelHandler(
                BodyInserters.fromResource(pixel),
                profileIdCookieName,
                hBaseConfiguration,
                TableName.valueOf(profileTable)
        );
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        List<HttpCookie> userIdCookies = serverRequest.cookies()
                .get(profileIdCookieName);
        boolean newProfile = userIdCookies == null || userIdCookies.isEmpty();
        String userId;
        if (newProfile) {
            userId = UUID.randomUUID()
                    .toString();
        } else {
            userId = userIdCookies.stream()
                    .reduce((firstCookie, lastCookie) -> lastCookie)
                    .map(HttpCookie::getValue)
                    .get();
        }

        ConnectionFactory.createAsyncConnection(hBaseConfiguration)
                .thenAccept(connection -> connection.getAdmin()
                        .tableExists(profileTable)
                        .thenCompose(tableExists -> {
                            if (!tableExists) {
                                return connection.getAdmin()
                                        .createTable(TableDescriptorBuilder.newBuilder(profileTable)
                                                .setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(
                                                        Bytes.toBytes("HISTORY")
                                                )
                                                        .setMaxVersions(100)
                                                        .build())
                                                .setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(
                                                        Bytes.toBytes("HEADER")
                                                )
                                                        .setMaxVersions(100)
                                                        .build())
                                                .build());
                            }

                            return CompletableFuture.completedFuture(null);
                        })
                        .thenCompose(response -> {
                            Put put = new Put(Bytes.toBytes(DigestUtils.md5Hex(userId)));

                            put.addColumn(
                                    Bytes.toBytes("HISTORY"),
                                    Bytes.toBytes("url"),
                                    Bytes.toBytes(serverRequest.uri()
                                            .toString())
                            );
                            serverRequest.headers()
                                    .asHttpHeaders()
                                    .forEach((key, values) -> values.forEach(value -> put.addColumn(
                                            Bytes.toBytes("HEADER"),
                                            Bytes.toBytes(key.toLowerCase()),
                                            Bytes.toBytes(value)
                                    )));

                            return connection.getTable(profileTable)
                                    .put(put);
                        })
                        .whenComplete((response, exception) -> {
                            if (exception != null) {
                                log.warn("Tracker does not track visit.", exception);
                            }

                            try {
                                connection.close();
                            } catch (IOException e) {
                                log.warn("Tracker does not close connection.", e);
                            }
                        }));

        if (newProfile) {
            return ServerResponse.ok()
                    .cookie(ResponseCookie.from(profileIdCookieName, userId)
                            .build())
                    .body(responseBody);
        }

        return ServerResponse.ok()
                .body(responseBody);
    }
}

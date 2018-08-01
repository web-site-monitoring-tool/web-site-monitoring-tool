package com.github.wsmt.reporter.handler;

import com.github.wsmt.reporter.service.ReportService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ReportHandler implements HandlerFunction<ServerResponse> {

    private final ReportService reportService;

    private ReportHandler(ReportService reportService) {
        this.reportService = reportService;
    }

    public static ReportHandler byService(ReportService reportService) {
        return new ReportHandler(reportService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        reportService.report();
        return ServerResponse.ok()
                .build();
    }
}

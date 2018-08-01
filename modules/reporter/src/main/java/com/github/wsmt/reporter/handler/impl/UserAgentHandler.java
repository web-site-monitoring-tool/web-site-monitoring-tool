package com.github.wsmt.reporter.handler.impl;

import com.github.wsmt.reporter.handler.Handler;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;


public class UserAgentHandler implements Handler {

    private String param = "AgentName";
    private static UserAgentAnalyzer userAgentAnalyzer;

    private UserAgentHandler() {
        userAgentAnalyzer = UserAgentAnalyzer
                .newBuilder()
                .withField(param)
                //.hideMatcherLoadStats()
                //.withCache(25000)
                .build();
    }

    public static UserAgentHandler create() {
        return new UserAgentHandler();
    }

    @Override
    public String handle(String inputRow) {
        UserAgent agent = userAgentAnalyzer.parse(inputRow);
        String browserName = agent.getValue(param);

        return browserName != null? browserName : "Unknown";
    }
}

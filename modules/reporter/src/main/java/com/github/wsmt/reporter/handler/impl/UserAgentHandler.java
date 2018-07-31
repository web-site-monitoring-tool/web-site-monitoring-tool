package com.github.wsmt.reporter.handler;

import org.springframework.beans.factory.annotation.Value;

public class UserAgentHandler implements Handler {

    private String chrome = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
    private String epiphany = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Safari/605.1.15 elementaryOS/0.4 (Loki) Epiphany/3.18.11";

    private UserAgentHandler() {

    }

    public static UserAgentHandler create() {
        return new UserAgentHandler();
    }

    @Override
    public String handle(String inputRow) {

        if (inputRow.equals(chrome)) {
            return "Chrome";
        } else if (inputRow.equals(epiphany)) {
            return "Epiphany";
        }

        return "Unknown";
    }
}
